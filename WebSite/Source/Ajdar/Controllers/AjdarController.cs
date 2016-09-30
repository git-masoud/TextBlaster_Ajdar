using Ajdar.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Ajdar.Controllers
{
    public class AjdarController : Controller
    {
        // GET: Ajdar
        public ActionResult Index(int id)
        {
            ViewBag.Id = id;
            return View();
        }
    }
}