using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(Ajdar.Startup))]
namespace Ajdar
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
