# Redesigning Authentication Screens

This plan outlines the visual redesign of the `LoginScreen` and `SignUpScreen` to improve aesthetics and user experience.

## User Review Required

> [!IMPORTANT]
> - I will introduce a new "Royal Blue" and "Deep Teal" color palette for a more professional e-commerce look.
> - Text fields and buttons will be fully rounded (Pill-shaped).
> - Only the action words ("Login" / "Sign Up") in the footer will be clickable and underlined.

## Proposed Changes

### [Component] UI Styling (Theme)

#### [MODIFY] [Color.kt](file:///home/rohit/Desktop/Android-projects/Ecommerce/ecommerce-app/app/src/main/java/com/rohit/ecom/ui/theme/Color.kt)
- Add new color constants (Primary, Secondary, Accent).

#### [MODIFY] [Theme.kt](file:///home/rohit/Desktop/Android-projects/Ecommerce/ecommerce-app/app/src/main/java/com/rohit/ecom/ui/theme/Theme.kt)
- Update `LightColorScheme` and `DarkColorScheme` to use the new palette.

### [Component] Authentication Screens

#### [MODIFY] [LoginScreen.kt](file:///home/rohit/Desktop/Android-projects/Ecommerce/ecommerce-app/app/src/main/java/com/rohit/ecom/presentation/screens/loginscreen/LoginScreen.kt)
- **Shape**: Update `OutlinedTextField` and `Button` to use `RoundedCornerShape(50)` for a pill-shaped look.
- **Loading State**: Modify the `Button` to remain visible during loading. Replace text with a `CircularProgressIndicator` (size 24.dp) inside the button when `uiState` is `Loading`.
- **Footer**: Implement `ClickableText` with an `AnnotatedString` to make only "Sign Up" underlined and clickable.

#### [MODIFY] [SignUpScreen.kt](file:///home/rohit/Desktop/Android-projects/Ecommerce/ecommerce-app/app/src/main/java/com/rohit/ecom/presentation/screens/signupscreen/SignUpScreen.kt)
- **Shape**: Update `OutlinedTextField` and `Button` to use `RoundedCornerShape(50)`.
- **Loading State**: Modify the `Button` to show a `CircularProgressIndicator` inside during loading.
- **Footer**: Implement `ClickableText` with an `AnnotatedString` to make only "Login" underlined and clickable.

## Verification Plan

### Manual Verification
- Deploy to emulator.
- **Visual Check**: Verify rounded corners on all inputs and buttons.
- **Color Check**: Verify new color scheme is applied.
- **Loading Check**: Click "Login" and verify the progress indicator appears *inside* the button while it's loading.
- **Navigation Check**: Verify that clicking the "Already have an account?" text only triggers navigation when clicking on the specific word "Login".
