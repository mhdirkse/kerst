# kerst
Drawing lots with the family for Christmas. Do you want to celebrate Christmas with presents? And do you want everyone to prepare
a present for someone? And do you want to link givers and receivers in advance such that everyone gives and receives one present?
Then you may be interested in this project. It is an open-source alternative for commercial sites.

# Usage
* You need a dedicated email address. This is needed for
technical reasons, but it is also nice to impersonate Santa Claus with his own email address.
I tested with a gmail address.
* IMPORTANT: You probably want to make sure that you will not see everyones lots. For Gmail,
you should make a filter for your new email address that deletes all messages coming FROM
itself. You should check that the sent folder remains empty during test runs.
* For Gmail, you need to turn on "Allow less secure apps". Go to your new Google account,
select "Sign-in & security" and then "Apps with account access".
* Under `src/main/resources`, make a property file named `credentials`. Here go the username
and the password of Santa Claus' email address. This address will send the emails. The property names
are `username` and `password`.
* Santa Claus tells a better story then "blabla". Edit `src/main/resources/body.html` to tell it.
* Edit class `com.github.mhdirkse.kerst.Member` with the members of your party. While testing,
I recommend that you give every member your email address. Then you do not disturb them with
your test runs.
* The mail opens with a picture presenting Santa Claus. You may want to replace this picture. It is
file `src/main/resources/home.jpg`.
* Some unit tests may fail now because the `Member` class was edited. Fixing them should be trivial.
* Google limits the number of emails you can send with their SMTP server. To test, please make some
code that runs `com.github.mhdirkse.kerst.MailSender`. You want to send only one email at a time
when you are testing your message contents.
* When all your tests were successful, do not forget to enter the real email addresses in class `Member`.
* Run the app to invite and draw lots!
