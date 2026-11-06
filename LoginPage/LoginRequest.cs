using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace LoginPage
{

    public class LoginRequest
    {
        // Models the login request sent to the backend, containing the employee ID and text-password
        public string EmployeeID { get; set; }
        public string Password { get; set; }
    }


}