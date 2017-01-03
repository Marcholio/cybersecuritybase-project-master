# cybersecuritybase-project-master
Project submitted to cybersecurity mooc by Helsinki University
Made to demonstrate some of top-10 vulnerabilities from https://www.owasp.org/index.php/Top_10_2013-Top_10

## How to run ##

1. Clone or download the repository
2. Open project in NetBeans
3. Press "Run Project"

## Vulnerabilities ##

### Issue 1: Cross-Site Request Forgery (CSRF) ###
Application is vulnerable to csrf attacks.

1. Make a POST-request with tool of your choosing (eg. OWASP ZAP)
POST http://localhost:8080/form HTTP/1.1
User-Agent: Mozilla/5.0 (Windows NT 6.3; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0
Pragma: no-cache
Cache-Control: no-cache
Content-Type: application/x-www-form-urlencoded
Content-Length: 20
Referer: http://localhost:8080/form
Host: localhost:8080

name=ZAP&address=ZAP
2. Verify that "ZAP" is on http://localhost:8080/done?name= list

### Issue 2: Cross-Site Scripting (XSS) ###
Address field is vulnerable to XSS attack as it parses all text as html without escaping.

1. Browse to http://localhost:8080/form
2. Insert following to address field:

```html
</p><script>alert('HACKED');</script><p>
```

3. Click submit and after that you should see popup at http://localhost:8080/done?name= with text HACKED everytime you click submit button.

### Issue 3: Broken authentication and insecure direct object references ###
Everyone can directly access adminview, which should only be accessible for admin users.

1. Browse to http://localhost:8080/adminview
2. You should see all users and addresses signed up for the event.


### Issue 4: Broken authentication ###
The admin credentials are vulnerable to online cracking.

1. Browse to http://127.0.0.1:8080/login
2. Try to guess the admin credentials (hint: password is same as username with three numbers at the end)
3. One could also bruteforce this since number of guesses is not limited.


### Issue 5: Using components with known vulnerabilities ###
Application has some dependencies that are not necessary, but have known vulnerabilities.

1. Run mvn dependency-check:check on project root
2. You'll get list of vulnerable dependencies, one of them is Apache Commons BeanUtils 1.6, which is not necessary for the application to work correctly.
