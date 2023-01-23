# Hotel Manager

Hotel Manager for AP Computer Science AB 2022-2023 in Ivan Rico's class

## Dependencies for running

- [Java 1.8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html) or [higher](https://adoptium.net/)

## Running

- First download the **[Latest release](https://github.com/luis-c465/Hotel/releases/latest)**
- Then run in the command line _(Or just double click on the file)_
  ```bash
  java -jar [path to downloaded jar file]
  ```

## Building

### Dependencies

- **[Maven v3.8.4](https://maven.apache.org/download.cgi)**

### How to build

Run the following in the [command line](https://www.freecodecamp.org/news/how-to-use-the-cli-beginner-guide/#how-to-locate-your-cli)

```bash
mvn clean compile assembly:single
```

- Then the **Executable Jar** `hotel.jar` will be in the root folder 🎉

## Project Requirements

Due: **Mon, Jan 30 2023** @1 AM

- User can book hotel rooms for a certain day of the month (You only need to use the month of January)
- Displays all rooms that are booked
- Displays all available rooms
- Rooms will be dirty for the day after the booking ends and will not be able to be booked if they are dirty.
- There are rooms with different prices (low, medium, high) that the user can choose to book for the customer.
- When a room is booked, no new customers can book that room for the day (can be multiple days in a row). Note: Exiting the program does not have to save the status of booking.
- The program displays the total amount the customer will pay.
