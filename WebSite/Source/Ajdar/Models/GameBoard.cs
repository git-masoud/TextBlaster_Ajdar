using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace Ajdar.Models
{
    public class GameBoard
    {
        public GameBoard()
        {
            Users = new List<AjdarUser>();
            NewShoots = new List<Shoot>();
        }
        [Key]
        [DatabaseGeneratedAttribute(DatabaseGeneratedOption.Identity)]
        public int ID { get; set; }
        [Required]
        public string Name { get; set; }
        public virtual List<AjdarUser> Users { get; set; }
        public bool? IsStarted { get; set; }
        public virtual List<Shoot> NewShoots { get; set; }
    }

    public class Shoot
    {
        public Shoot()
        { }
        public Shoot(string userName, string targetUserName, float cER)
        {
            CER = cER;
            UserName = userName;
            TargetUserName = targetUserName;
            Id = Guid.NewGuid();
        }
        [Key]
        public Guid Id { get; set; }
        [Required]
        public string UserName { get; set; }
        [Required]
        public string TargetUserName { get; set; }
        public float CER { get; set; }
        [Required]
        public int BoardId { get; set; }
        [ForeignKey("BoardId")]
        public virtual GameBoard Board { get; set; }
    }
    public class Sentence
    {
        public Sentence()
        { }
        [Key]
        [DatabaseGeneratedAttribute(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }
        public string Text { get; set; }
    }
}