namespace LoginPage;

public partial class TableLayoutPage : ContentPage
{
    private bool statusChanging = false;
    private enum TableStatus
    {
        Clean,
        Occupied,
        Dirty
    }
    private TableStatus currentStatus = TableStatus.Clean;
    private readonly TableService _tableService;
    private readonly OrderService _orderService;
    public TableLayoutPage(TopBarView topBar, WaiterTopBarView waiterTopBar, TableService tableService, OrderService orderService)
	{
		InitializeComponent();
        _tableService = tableService;
        _orderService = orderService;   
        // Changes the top bar depending on role
        if (UserSession.Role == "Manager")
        {
            TopBarContainer.Content = topBar;
            topBar.SetTableLayoutUnderline();
        } else if (UserSession.Role == "Wait Staff")
        {
            TopBarContainer.Content = waiterTopBar;
            waiterTopBar.SetTableLayoutUnderline();
        }
        
	}

    // Shows the table pop up to start adding, viewing, and checking out orders for the selected table. Also used to change table colors
    private async void ShowTablePopUpMenu(object sender, EventArgs e)
    {
        Button button = (Button)sender;
        TableSession.TableId = button.Text;
        // Debug lines added because for some reason, Colors.Green is different than button.BackgroundColor = "Green", even though they print the same color values in RGB.
        System.Diagnostics.Debug.WriteLine($"Button color: {button.BackgroundColor}");
        System.Diagnostics.Debug.WriteLine($"Colors.Green: {Colors.Green}");
        System.Diagnostics.Debug.WriteLine($"Are equal: {button.BackgroundColor == Colors.Green}");

        if (!statusChanging)
        {
            if (button.BackgroundColor == Colors.Yellow)
            {
                BorderPopUp.IsVisible = true;
                TableNameLabel.Text = button.Text;
            } else if (button.BackgroundColor == Colors.Red)
            {
                await DisplayAlert("Table Dirty", "Table is dirty, please clean the table before adding orders", "Ok");
            } else
            {
                BorderPopUp.IsVisible = true;
                TableNameLabel.Text = button.Text;
            }

            
        } else
        {
            switch (currentStatus)
            {
                // if statements make sure that wait staff cannot change a table from dirty to anything else, since that is the job of either the busboy or a manager.
                case TableStatus.Clean:
                    if (UserSession.Role == "Wait Staff" && button.BackgroundColor == Colors.Red)
                    {
                        await DisplayAlert("Error", "Waiters cannot change tables from dirty to clean, please contact a manager or busboy", "Ok");
                        break;
                    }
                    button.BackgroundColor = Colors.Green;
                    await _tableService.UpdateStatus(new Table { TableID = button.Text, Status = "Clean", EmployeeID = UserSession.EmployeeID});
                    break;
                case TableStatus.Occupied:
                    if (UserSession.Role == "Wait Staff" && button.BackgroundColor == Colors.Red)
                    {
                        await DisplayAlert("Error", "Waiters cannot change tables from dirty to clean, please contact a manager or busboy", "Ok");
                        break;
                    }
                    button.BackgroundColor = Colors.Yellow;
                    await _tableService.UpdateStatus(new Table { TableID = button.Text, Status = "Occupied", EmployeeID = UserSession.EmployeeID});
                    break;
                case TableStatus.Dirty:
                    button.BackgroundColor = Colors.Red;
                    await _tableService.UpdateStatus(new Table { TableID = button.Text, Status = "Dirty", EmployeeID = UserSession.EmployeeID});
                    break;
            }
        }
        
    }

    // Loads the table colors based on their status
    private async void OnTableLoaded(object sender, EventArgs e)
    {

        Button button = (Button)sender;
        var table = await _tableService.GetTableByTableId(button.Text);
        
            
        if (table != null)
        {
            switch (table.Status)
            {
                case "Clean":
                    
                    button.BackgroundColor = Colors.Green;
                    break;
                case "Occupied":
                    
                    button.BackgroundColor = Colors.Yellow;
                    break;
                case "Dirty":
                    button.BackgroundColor = Colors.Red;
                    break;
            }
        }
        
    }

    // Hides the table pop up
    private void Close_Button_Clicked(object sender, EventArgs e)
    {
        BorderPopUp.IsVisible = false;
    }

    // Navigates to the add order menu page and sets the current table session to the selected table
    private async void AddOrderButton_Clicked(object sender, EventArgs e)
    {
        TableSession.TableId = TableNameLabel.Text;
        await Shell.Current.GoToAsync($"//{nameof(AddOrderMenuPage)}");
    }

    // Navigates to the view order page and sets the current table session to the selected table, also checks if there are any orders for the table before navigating
    private async void CheckoutButton_Clicked(object sender, EventArgs e)
    {
        List<Order> orders = await _orderService.GetOrdersByTableId(TableSession.TableId);
        if (orders == null || !orders.Any())
        {
            await DisplayAlert("No Orders", "There are no orders for this table.", "OK");
            return;
        }
        await Shell.Current.GoToAsync($"//{nameof(CheckoutPaymentMethodPage)}");
    }

    private void OnDoneClicked(object sender, EventArgs e)
    {
        statusChanging = false;
        DoneButton.IsVisible = false;
    }

    // These functions set the current status that tables are about to changed to
    private void OnCleanClicked(object sender, EventArgs e)
    {
        currentStatus = TableStatus.Clean;
        statusChanging = true;
        DoneButton.IsVisible = true;
    }

    private void OnOccupiedClicked(object sender, EventArgs e)
    {
        currentStatus = TableStatus.Occupied;
        statusChanging = true;
        DoneButton.IsVisible = true;
    }

    private void OnDirtyClicked(object sender, EventArgs e)
    {
        currentStatus = TableStatus.Dirty;
        statusChanging = true;
        DoneButton.IsVisible = true;
    }
}