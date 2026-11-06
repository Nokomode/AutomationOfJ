using System;

public static class UserSession
{
    // This class holds the current user's session information, such as their employee ID and role.
    public static string EmployeeID { get; set; }
    public static string Role { get; set; }
    public static string FirstName { get; set; }
    public static string LastName { get; set; }


    // Used to clear the session information when the user logs out.
    public static void Clear()
    {
        EmployeeID = null;
        Role = null;
        FirstName = null;
        LastName = null;
    }
}
