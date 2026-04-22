namespace LoginPage;

public partial class TablePopUpView : ContentView
{ 
	public TablePopUpView()
	{
		InitializeComponent();
	}
    private void Button_Clicked(object sender, EventArgs e)
	{
		BorderPopUp.IsVisible = false;
	}
}