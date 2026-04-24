namespace LoginPage;

public partial class CheckoutPaymentMethodPage : ContentPage
{
	public CheckoutPaymentMethodPage()
	{
		InitializeComponent();
	}

    private async void Button_Clicked(object sender, EventArgs e)
	{
		if(CardButton.IsChecked){
			Navigation.PushAsync(new CheckoutCardPaymentContactInfoPage());
		}
		else if (CashButton.IsChecked)
		{
			Navigation.PushAsync(new CheckoutCashPaymentContactInfoPage());
		}
		else
		{
			await DisplayAlertAsync("Error", "Please select a payment method.", "Close");
		}
	}
}