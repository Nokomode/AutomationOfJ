using Microsoft.Extensions.Logging;

namespace LoginPage
{
    public static class MauiProgram
    {
        public static MauiApp CreateMauiApp()
        {
            var builder = MauiApp.CreateBuilder();
            builder
                .UseMauiApp<App>()
                .ConfigureFonts(fonts =>
                {
                    fonts.AddFont("OpenSans-Regular.ttf", "OpenSansRegular");
                    fonts.AddFont("OpenSans-Semibold.ttf", "OpenSansSemibold");
                });

            // Register services, only one of each service for the entire application
            builder.Services.AddSingleton<HttpClient>();
            builder.Services.AddSingleton<AuthService>();
            builder.Services.AddSingleton<ShiftService>();
            builder.Services.AddSingleton<UserService>();
            builder.Services.AddSingleton<MenuService>();
            builder.Services.AddSingleton<OrderService>();
            builder.Services.AddSingleton<TableService>();
            builder.Services.AddSingleton<InventoryService>();


            // Register pages, can have multiple instances of each page
            builder.Services.AddTransient<LoginPage>();
            builder.Services.AddTransient<RegisterPage>();
            builder.Services.AddTransient<RefundsPage>();
            builder.Services.AddTransient<EmployeeProfilesPage>();
            builder.Services.AddTransient<SalesPage>();
            builder.Services.AddTransient<InventoryPage>();
            builder.Services.AddTransient<TableLayoutPage>();
            builder.Services.AddTransient<ManagerDashboardPage>();
            builder.Services.AddTransient<AllEmployeesPage>();
            builder.Services.AddTransient<OrderQueuePage>();
            builder.Services.AddTransient<WaiterDashboardPage>();
            builder.Services.AddTransient<AddOrderMenuPage>();
            builder.Services.AddTransient<CheckoutCardPaymentContactInfoPage>();
            builder.Services.AddTransient<CheckoutCardReviewPage>();
            builder.Services.AddTransient<CheckoutPaymentMethodPage>();
            builder.Services.AddTransient<CheckoutCashPaymentContactInfoPage>();

            builder.Services.AddTransient<TopBarView>();
            builder.Services.AddTransient<WaiterTopBarView>();


            // Add logging
#if DEBUG
            builder.Logging.AddDebug();
#endif

            return builder.Build();
        }
    }
}
