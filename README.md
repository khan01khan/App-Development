# Restaurant Management System (Android Application)

## Overview
The Restaurant Management System is an Android application developed using Java and XML. The application digitalizes restaurant operations by providing separate interfaces for Admin and Customer roles. Admin users can manage menu items, users, and orders, while customers can browse the menu, add items to a cart, and place orders. The system focuses on simplicity, usability, and efficient workflow management.

## Tech Stack
-   **Programming Language:** Java
-   **UI Design:** XML
-   **IDE:** Android Studio
-   **Architecture:** Activity and Fragment-based architecture
-   **Build System:** Gradle

## Project Structure
-   **Activities:** Handles major screens like authentication (Login & Signup), user-specific dashboards (Admin & Customer), cart management, and menu item management.
-   **Fragments:** Manages different sections within activities, such as home screen content, menu display, user orders, and sections of the admin dashboard.
-   **Adapters:** Binds data to UI components in lists, such as displaying menu items, cart items, orders, and users in a `RecyclerView`.
-   **Model classes:** Defines the data structure for core entities like `User`, `MenuItem`, `Order`, and `CartItem`.
-   **Resource files:** Contains all non-code assets, including XML layouts for UI, drawable images, and the navigation header design.

## Key Features
-   **Splash Screen:** An initial screen that displays when the application is launched.
-   **User Authentication:** Secure Login and Signup functionality for users.
-   **Role-Based Access:** Differentiates between 'Admin' and 'Customer' roles, providing different UIs and functionalities for each.
-   **Menu Management System:** Allows admins to add, update, or remove food items from the menu.
-   **Cart and Order Processing:** Customers can add items to their cart and place an order.
-   **User Management:** Admins have the ability to view and manage registered users.
-   **Clean and user-friendly interface:** Intuitive and easy-to-navigate UI for a smooth user experience.

## Application Workflow
1.  The application starts with a **Splash Screen**.
2.  The user is prompted to **Login** or **Signup**.
3.  Based on the user's role, they are navigated to the appropriate **Dashboard** (Admin or Customer).
4.  A **Customer** can browse the menu, select items, add them to the cart, and place an order.
5.  An **Admin** can manage menu items, view and manage users, and process incoming orders.

## Installation & Setup
1.  **Clone or download the project** repository to your local machine.
2.  **Open the project** in Android Studio.
3.  **Sync Gradle** by clicking on the 'Sync Project with Gradle Files' button.
4.  **Run the application** on an Android emulator or a physical device.

## Future Enhancements
-   Integration of an online payment gateway.
-   Implementation of a persistent database using Firebase or SQLite.
-   Real-time order status updates for customers.
-   A dashboard for the admin to view sales and analytics.

## Contributors
-   Zoya Gul (232202050)
-   Minahil Shujah Satti (232202049)
-   Wajeeha Naz (232202028)

## License
This project is intended for academic and learning purposes.
