# Immersive Modular Details Screen Implementation

This plan outlines the creation of a high-quality, modular Product Details screen. Following best practices, the UI will be decomposed into reusable components in new files, while `DetailsScreen.kt` serves as the main state coordinator.

## User Review Required

> [!IMPORTANT]
> Since I cannot change existing files besides the designated `DetailsScreen.kt` entry point, I will implement all new UI logic by creating new files in the `detailsscreen` package.
> I will assume an `onBackClick` lambda is needed for a proper UX, which I will add to the `DetailsScreen` signature.

## Proposed Changes

### [Component] Product Details UI

#### [NEW] [ProductDetailHeader.kt](file:///home/rohit/Desktop/Android-projects/Ecommerce/ecommerce-app/app/src/main/java/com/rohit/ecom/presentation/screens/detailsscreen/components/ProductDetailHeader.kt)
- Contains the large product image with a back button overlay.
- Uses `ContentScale.Fit` or `Crop` depending on the aspect ratio for a premium feel.

#### [NEW] [ProductDetailInfo.kt](file:///home/rohit/Desktop/Android-projects/Ecommerce/ecommerce-app/app/src/main/java/com/rohit/ecom/presentation/screens/detailsscreen/components/ProductDetailInfo.kt)
- Displays the product Name, Category (as a badge), and Stock status.
- Implements a clean typography hierarchy using Material 3 `TitleLarge` and `BodyMedium`.

#### [NEW] [ProductDetailDescription.kt](file:///home/rohit/Desktop/Android-projects/Ecommerce/ecommerce-app/app/src/main/java/com/rohit/ecom/presentation/screens/detailsscreen/components/ProductDetailDescription.kt)
- A dedicated section for the product description with a "Description" header.
- Uses selectable text for better accessibility.

#### [NEW] [ProductDetailBottomBar.kt](file:///home/rohit/Desktop/Android-projects/Ecommerce/ecommerce-app/app/src/main/java/com/rohit/ecom/presentation/screens/detailsscreen/components/ProductDetailBottomBar.kt)
- A fixed bottom bar containing "Add to Cart" (Outlined) and "Buy Now" (Filled) buttons.
- Designed to be persistent while the rest of the content scrolls.

#### [MODIFY] [DetailsScreen.kt](file:///home/rohit/Desktop/Android-projects/Ecommerce/ecommerce-app/app/src/main/java/com/rohit/ecom/presentation/screens/detailsscreen/DetailsScreen.kt)
- Updates the `Success` state to use a `Scaffold` that combines the new components.
- Implements `verticalScroll` for the main content area.
- Handles the `onBackClick` event.

## Verification Plan

### Manual Verification
- Deploy the app to the 'Medium Phone' emulator.
- Navigate from the Home screen to a Product Detail.
- Verify the following:
    - **Loading State**: Circular indicator is centered.
    - **Success State**:
        - Image is sharp and correctly scaled.
        - Back button returns to the Home screen.
        - Content is scrollable if it exceeds screen height.
        - Bottom buttons stay pinned to the bottom.
    - **Error State**: Friendly error message with a retry option (if applicable).
