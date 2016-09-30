using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;
using System.Xml.Serialization;

namespace Ajdar.Models
{
    public class AjdarUser
    {
        public AjdarUser()
        { }

        public AjdarUser(string name)
        {
            Name = name;
            Health =100;
            WPN = 100;
            Errors = 0;
            Status = 1;
        }
        public AjdarUser(JObject jUser)
        {
           
            Name = (string)jUser["Name"];
            Health = (int)jUser["Health"];
            WPN = (int)jUser["WPN"];
            Errors = (int)jUser["Errors"];
            Status = (int)jUser["Status"];
        }
        [Key]
        public string Name { get; set; }
        public int Health { get; set; }
        public int WPN { get; set; }
        public int Errors { get; set; }
        //0=loose
        //1=active
        //2=Win
        public int Status { get; set; }
        [Required]
        public int BoardId { get; set; }
        [XmlIgnore]
        [ForeignKey("BoardId")]
        public virtual GameBoard Board { get; set; }
    }
}