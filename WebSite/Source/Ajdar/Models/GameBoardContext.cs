using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace Ajdar.Models
{
    public class GameBoardContext :  DbContext
    {
        public GameBoardContext() : base()
        {
            Database.SetInitializer<GameBoardContext>(new GameBoardDBInitializer());
        }
      
        public DbSet<Shoot> Shoots { get; set; }
        public DbSet<AjdarUser> AjdarUsers { get; set; }
        public DbSet<GameBoard> GameBoards { get; set; }
        public DbSet<Sentence> Sentences { get; set; }
    }
}