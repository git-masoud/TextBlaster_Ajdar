using Ajdar.Models;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json.Serialization;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Script.Serialization;

namespace Ajdar.Controllers
{
    public class AjdarApiController : ApiController
    {
        GameBoardContext context = new GameBoardContext();
        [HttpGet]
        public int MakeBoard(string name, string adminName)
        {
            var board = new GameBoard() { Name = name };
            var lastUser = context.AjdarUsers.FirstOrDefault(item => item.Name == adminName);
            if (lastUser != null)
                context.AjdarUsers.Remove(lastUser);
            board.Users.Add(new AjdarUser(adminName));
            context.GameBoards.Add(board);
            context.SaveChanges();
            return board.ID;
        }
        [HttpGet]
        public List<GameBoard> GetBoards()
        {
            var boards = context.GameBoards.Where(item => !item.IsStarted.HasValue).ToList();
            return boards;
        }
        [HttpGet]
        public bool RequestToStartGame(int boardId)
        {
            var board = context.GameBoards.FirstOrDefault(item => item.ID == boardId);
            if (board != null)
            {
                board.IsStarted = false;
                context.SaveChanges();
                return true;
            }
            else
                return false;
        }
        [HttpGet]
        public bool StartGame(int boardId)
        {
            var board = context.GameBoards.FirstOrDefault(item => item.ID == boardId);
            if (board != null)
            {
                if (board.IsStarted == false)
                {
                    board.IsStarted = true;
                    context.SaveChanges();
                    return true;
                }

                else
                    return false;
            }
            else
                return false;
        }
        [HttpGet]
        public bool IsGameStarted(int boardId)
        {
            var board = context.GameBoards.FirstOrDefault(item => item.ID == boardId);
            if (board != null)
            {
                if (board.IsStarted == true)
                    return true;
                else
                    return false;
            }
            else
                return false;
        }
        Random r = new Random();
        [HttpGet]
        public bool Shoot(int boardId, string userName, float cer)
        {
            var board = context.GameBoards.FirstOrDefault(item => item.ID == boardId);
            if (board != null)
            {
                var user = board.Users.FirstOrDefault(item => item.Name == userName);
                if (user != null)
                {
                    board.NewShoots.Add(new Models.Shoot(userName, board.Users.Where(item => item.Name != userName).ToArray()[r.Next(0, board.Users.Count() - 1)].Name, cer));
                    context.SaveChanges();
                    return true;
                }
                else
                    return false;

            }
            else
                return false;
        }
        [HttpGet]
        public List<Shoot> GetNewShoots(int boardID)
        {
            var board = context.GameBoards.FirstOrDefault(item => item.ID == boardID);
            if (board != null)
            {
                return board.NewShoots;
            }
            else
                return null;
        }
        [HttpGet]
        public void Shooted(Guid ID)
        {

            var shoot = context.Shoots.FirstOrDefault(item => item.Id == ID);
            if (shoot != null)
            {
                context.Shoots.Remove(shoot);
                context.SaveChanges();
            }


        }
        [HttpGet]
        public List<AjdarUser> GetUsers(int ID)
        {
            var board = context.GameBoards.FirstOrDefault(item => item.ID == ID);
            if (board != null)
                return board.Users;
            else
                return null;
        }
        // GET: api/Default
        [HttpGet]
        public bool JoinUser(string name, int boardId)
        {
            var user = new AjdarUser(name);
            var board = context.GameBoards.FirstOrDefault(item => item.ID == boardId);
            if (board != null)
            {
                var lastUser = context.AjdarUsers.FirstOrDefault(item => item.Name == name);
                if (lastUser != null)
                    context.AjdarUsers.Remove(lastUser);
                board.Users.Add(user);
                context.SaveChanges();
                return true;
            }
            else
                return false;
        }
        [HttpGet]
        public bool UpdateUsersStatus(string jsonUsers, int boardId)
        {
            var board = context.GameBoards.FirstOrDefault(item => item.ID == boardId);
            if (board != null)
            {
                JArray a = JArray.Parse(jsonUsers);

                foreach (JObject o in a.Children<JObject>())
                {
                    var newUser = new AjdarUser(o);
                    var oldUser = board.Users.FirstOrDefault(item => item.Name == newUser.Name);
                    oldUser.Errors = newUser.Errors;
                    oldUser.Health = newUser.Health;
                    oldUser.WPN = newUser.WPN;
                    oldUser.Status = newUser.Status;
                    context.SaveChanges();
                }
            }
            return true;
        }
        [HttpGet]
        public Sentence GetNewSentence()
        {
            int sentenceId = new Random().Next(1, 110);

            return context.Sentences.First(item => item.Id == sentenceId);
        }
        [HttpGet]
        public AjdarUser GetUserStatus(string name, int boardId)
        {
            var board = context.GameBoards.FirstOrDefault(item => item.ID == boardId);
            if (board != null)
            {
                var user = board.Users.FirstOrDefault(item => item.Name == name);
                if (user != null)
                {
                    return user;
                }
                else
                    return null;
            }
            else
                return null;
        }
    }
}
