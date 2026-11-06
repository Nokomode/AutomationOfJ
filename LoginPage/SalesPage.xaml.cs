namespace LoginPage;

public partial class SalesPage : ContentPage
{
	private readonly MenuService _menuService;


	public SalesPage(MenuService menuService, TopBarView topBar)
	{
		_menuService = menuService;
		InitializeComponent();
        TopBarContainer.Content = topBar;
        topBar.SetSalesUnderline();
        BindingContext = new MenuItemViewModel(_menuService);
    }

    protected override void OnAppearing()
    {
        base.OnAppearing();
        // Reload data every time the page becomes visible
        if (BindingContext is MenuItemViewModel vm)
        {
            vm.LoadMenuItems();
        }
    }
}