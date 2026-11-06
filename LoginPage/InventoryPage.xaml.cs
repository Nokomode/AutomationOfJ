namespace LoginPage;

public partial class InventoryPage : ContentPage
{
    private readonly InventoryService _inventoryService;
    public InventoryPage(TopBarView topBar, InventoryService inventoryService)
	{
		InitializeComponent();
        TopBarContainer.Content = topBar;
        topBar.SetInventoryUnderline();
        _inventoryService = inventoryService;
        BindingContext = new InventoryItemViewModel(_inventoryService);
    }

    protected override void OnAppearing()
    {
        base.OnAppearing();
        // Reload inventory items every time the page becomes visible
        if (BindingContext is InventoryItemViewModel vm)
        {
            vm.LoadInventoryItems();
        }
    }

    
}