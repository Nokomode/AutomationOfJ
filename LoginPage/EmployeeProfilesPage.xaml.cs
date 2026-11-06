using System.Diagnostics;

namespace LoginPage;

public partial class EmployeeProfilesPage : ContentPage
{
    private readonly UserService _userService;
    private readonly ShiftService _shiftService;
    private readonly TableService _tableService; 
    private readonly User _user;
    public EmployeeProfilesPage(TopBarView topBar, UserService userService, ShiftService shiftService, TableService tableService, User user)
	{
		InitializeComponent();
        _userService = userService;
        _shiftService = shiftService;
        _tableService = tableService;
        TopBarContainer.Content = topBar;
        topBar.SetEmployeeProfilesUnderline();
        BindingContext = new ShiftsViewModel(_shiftService);
        _user = user;

        // Sets label text to user information
        WelcomeNameLabel.Text = $"Welcome, {_user.FirstName} {_user.LastName}";
        WelcomeRoleLabel.Text = $"Role: {_user.Role}";

        if (_user.Role != "Wait Staff")
        {
            TableLayoutBorder.IsVisible = false;
            


        } else
        

        EmployeeIdLabelText.Text = $"{_user.EmployeeID}";
        EmailLabelText.Text = $"Email: {_user.Email}";
        PhoneLabelText.Text = $"Phone: {_user.Phone}";
    }

    // Reloads shifts and table assignments every time the page appears
    protected override async void OnAppearing()
    {
        base.OnAppearing();
        // Reload data every time the page becomes visible
        if (BindingContext is ShiftsViewModel vm)
        {
            vm.LoadShifts(_user);
        }

        // Only load table assignments for wait staff
        if (_user.Role == "Wait Staff")
        {
            List<Table> tables = await _tableService.GetTablesByEmployeeID(_user.EmployeeID);
            string tableNumbers = string.Join(", ", tables.Select(t => t.TableID));
            TableAssignmentLabel.Text = $"{tableNumbers}";
        }
    }

    // Sets entry fields to visible and pre-fills them with user information for editing
    private void OnEditClicked(object sender, EventArgs e)
    {
        // Pre-fill all entry fields
        FirstNameEntry.Text = _user.FirstName;
        LastNameEntry.Text = _user.LastName;
        RolePicker.SelectedItem = _user.Role;
        EmailEntry.Text = _user.Email;
        PhoneEntry.Text = _user.Phone;

        // Switch to edit mode
        NameEditMode.IsVisible = true;
        EmployeeIdLabelText.IsVisible = false;
        ContactEditMode.IsVisible = true;
        EditButton.IsVisible = false;
        SaveButton.IsVisible = true;
        CancelButton.IsVisible = true;
    }


    // Saves changes to user profile and updates labels with new information
    private async void OnSaveClicked(object sender, EventArgs e)
    {
        // Update user object with new values
        _user.FirstName = FirstNameEntry.Text;
        _user.LastName = LastNameEntry.Text;
        _user.Role = RolePicker.SelectedItem?.ToString() ?? _user.Role;
        _user.Email = EmailEntry.Text;
        _user.Phone = PhoneEntry.Text;

        UserRequest userRequest = new UserRequest
        {
            EmployeeID = _user.EmployeeID,
            FirstName = _user.FirstName,
            LastName = _user.LastName,
            Role = _user.Role,
            Email = _user.Email,
            Phone = _user.Phone
        };

        bool success = await _userService.UpdateUser(userRequest);

        if (success)
        {
            WelcomeNameLabel.Text = $"Welcome, {_user.FirstName} {_user.LastName}";
            WelcomeRoleLabel.Text = $"Role: {_user.Role}";
            EmployeeIdLabelText.Text = $"{_user.EmployeeID}";
            EmailLabelText.Text = $"Email: {_user.Email}";
            PhoneLabelText.Text = $"Phone: {_user.Phone}";
            SwitchToViewMode();
        }
        else
        {
            await DisplayAlertAsync("Error", "Failed to update profile.", "OK");
        }
    }

    // Discards changes and switches back to view mode
    private void OnCancelClicked(object sender, EventArgs e)
    {
        SwitchToViewMode();
    }

    // Helper method to switch UI back to view mode
    private void SwitchToViewMode()
    {
        NameEditMode.IsVisible = false;
        EmployeeIdLabelText.IsVisible = true;
        ContactEditMode.IsVisible = false;
        EditButton.IsVisible = true;
        SaveButton.IsVisible = false;
        CancelButton.IsVisible = false;
    }
}