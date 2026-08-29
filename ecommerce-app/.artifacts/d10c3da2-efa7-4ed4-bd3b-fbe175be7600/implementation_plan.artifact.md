# Fix Profile Screen NullPointerException

This plan addresses the `NullPointerException` crashing the app when opening the Profile screen. The crash is caused by `null` values for `address` and `mobileNumber` in the backend response, which are currently defined as non-nullable `String` in the data model.

## User Review Required

> [!NOTE]
> I will be making some fields in the `ProfileData` class nullable to correctly reflect the data coming from your backend.

## Proposed Changes

### [Component] Data Layer

#### [MODIFY] [ProfileData.kt](file:///home/rohit/Desktop/Android-projects/Ecommerce/ecommerce-app/app/src/main/java/com/rohit/ecom/data/remote/profile/dto/ProfileData.kt)
- Change `address: String` to `address: String?`.
- Change `mobileNumber: String` to `mobileNumber: String?`.

### [Component] UI Components

#### [MODIFY] [ProfileItem.kt](file:///home/rohit/Desktop/Android-projects/Ecommerce/ecommerce-app/app/src/main/java/com/rohit/ecom/presentation/screens/profilescreen/components/ProfileItem.kt)
- Change `value: String` parameter to `value: String?`.
- Update the display logic to handle `null` or empty strings by showing "Not set".

#### [MODIFY] [EditProfileDialog.kt](file:///home/rohit/Desktop/Android-projects/Ecommerce/ecommerce-app/app/src/main/java/com/rohit/ecom/presentation/screens/profilescreen/components/EditProfileDialog.kt)
- Change `currentAddress: String` and `currentMobile: String` to `String?`.
- Initialize local state with an empty string if the provided values are `null`.

## Verification Plan

### Manual Verification
- **Fetch Profile**: Verify that the Profile screen loads without crashing when `address` or `mobileNumber` are `null`.
- **Display**: Ensure "Not set" is displayed for `null` fields.
- **Editing**: Open the edit dialog and verify that it doesn't crash and shows empty fields for `null` values.
