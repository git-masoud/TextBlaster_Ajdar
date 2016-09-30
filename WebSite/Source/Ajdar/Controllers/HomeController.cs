using Ajdar.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Ajdar.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
           
            
            GameBoardContext context = new GameBoardContext();
            var boards = context.GameBoards.Where(item => item.IsStarted == false).ToList();
            return View(boards); 
        }
        public ActionResult About(int id)
        {
            //var user = "test" + new Random().Next(10, 100);
            //new AjdarApiController().JoinUser(user, id);
            return View();// GameBoards.Boards.FirstOrDefault(item => item.ID == id).Users);
        }

        public ActionResult Contact(int? boardId)
        {
            GameBoardContext context = new GameBoardContext();
            if (boardId == -1)
            {
                foreach(var gb in context.GameBoards)
                     context.GameBoards.Remove(gb);
                context.SaveChanges();
            }
            return View();
        }
    }
}