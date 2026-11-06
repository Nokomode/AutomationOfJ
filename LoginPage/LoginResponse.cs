using System;

public class LoginResponse
{
    // Models the response from the backend after a login attempt, containing user information and role
    public string Role { get; set; }
	public string Username { get; set; }
	public string FirstName { get; set; }
	public string LastName { get; set; }

    public LoginResponse()
	{
	}
}
