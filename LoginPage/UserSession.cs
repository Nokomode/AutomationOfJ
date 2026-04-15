using System;

public static class UserSession
{
    public static string EmployeeID { get; set; }
    public static string Role { get; set; }

    public static void Clear()
    {
        EmployeeID = null;
        Role = null;
    }
}
