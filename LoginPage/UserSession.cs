using System;

public static class UserSession
{
    public static string Username { get; set; }
    public static string Role { get; set; }

    public static void Clear()
    {
        Username = null;
        Role = null;
    }
}
