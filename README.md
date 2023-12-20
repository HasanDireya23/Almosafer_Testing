# Automated Testing for Almosafer Website using Selenium - [Link](https://www.almosafer.com)

## Overview
This project comprises automated tests conducted via Selenium WebDriver for the traveler website. The tests cover various scenarios to validate the website's functionality and behavior.

## Test Cases

### Test Case 1: Initial Page Assertions
- Navigate to URL Almosafer.
- Perform assertions on the initial page:
  - Verify default language is set to English (EN).
  - Ensure default currency is set to Saudi Riyal (SAR).
  - Check the correctness of contact numbers (manual verification).
  - Confirm the presence of the "qitaf" logo in the footer.
  - Ensure the Hotels search tab is not selected by default.
  - Verify the default departure date for flights is set to "today + 1 day."
  - Confirm the default return date for flights is set to "today + 2 days."
  - Perform any additional assertions deemed necessary.

### Test Case 2: Language Change and Hotel Search
- Use a random method to change the language between Arabic (AR) and English (EN).
- Assert that the language changes as per selection.
- Switch to the hotel search tab:
  - Enter a value in the location field based on the current language.
  - Click on the first result from the autocomplete list.

### Test Case 3: Hotel Search and Results
- Randomly select "1 room, 2 adults, 0 children" or "1 room, 1 adult, 0 children" option.
- Click on the 'Search Hotels' button.
- On the new page (Search Results page):
  - Wait for complete loading (using loading bar or API).
  - Perform critical assertions after page load.

### Test Case 4: Sorting Hotels by Price
- Apply sorting by "LOWEST PRICE."
- Assert that the results on the first page are sorted from lowest to highest price.

- Perform manual verification for assertions that require human validation.
- Use explicit waits to handle asynchronous loading elements.
- Validate assertions meticulously to ensure the accuracy of test results.
