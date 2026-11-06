

namespace LoginPage;

public partial class RefundsPage : ContentPage
{
	private readonly OrderQueueViewModel _viewModel;
	private readonly OrderService _orderService;

	public RefundsPage(TopBarView topBar, OrderService orderService)
	{
		InitializeComponent();
        TopBarContainer.Content = topBar;
        topBar.SetRefundsUnderline();
        _orderService = orderService;
        _viewModel = new OrderQueueViewModel(orderService);
        BindingContext = _viewModel;
    }

    protected override async void OnAppearing()
    {
        base.OnAppearing();
        await _viewModel.LoadRefundOrdersAsync();
    }
}