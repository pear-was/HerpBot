# HerpBot
#### Simple IRC bot with weather retrieval and URL shortener.

## Features:
* Displays time
* Retrieves weather information for inputted zipcode
* Shortens user-submitted URL

## Usage:
* Time: `!time`

   Example:
   ```
   !time
   The time is now Sat Nov 11 03:15:59 CST 2017
   ```
   
* Weather: `!weather (zipcode)`

   Example:
   ```
   !weather 75080
   The temperature in Richardson is 57F and the current conditions are: overcast clouds
   ```
   **Note:** This currently only supports US zip codes.
   
* Shorten URL: `!short (URL)`

   Example:
   ```
   !short http://itisamystery.com/
   https://goo.gl/eL81mY
   ```
   
## Notes:
Utilizes a typical IRC method of recognizing user commands to the bot by using `!command`, to avoid confusion.

By default connects to the `#herpbottest` channel on `irc.freenode.net` as `HerpBot`. This can be changed in `HerpBot.java`.

This was a project done for my CS2336 class.

## Libraries:
#### PircBot: http://www.jibble.org/pircbot.php
#### Gson 2.6: https://repo1.maven.org/maven2/com/google/code/gson/gson/2.6.2/
#### Apache Commons-Validator: http://commons.apache.org/proper/commons-validator/download_validator.cgi