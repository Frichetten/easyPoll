(function () {
    console.log("Getting Elements");   
    //Get elements
    const txtEmail = document.getElementById('txtEmail');
    const txtPassword = document.getElementById('txtPassword');
    const btnLogin = document.getElementById('btnLogin');
    const btnSignup = document.getElementById('btnSignup');
    const btnLogout = document.getElementById('btnLogout');
    
    console.log("Creating Login");    
    btnLogin.addEventListener('click', e => {
        //Get Email and Password from Input
        const email = txtEmail.value;
        const pass = txtPassword.value;
        const auth = firebase.auth();
        
        //Sign in with Email and Password
        const promise = auth.signInWithEmailAndPassword(email,pass);
        console.log("Logging In");
        promise.catch(e => console.log(e.message));
        firebase.auth().onAuthStateChanged(function(user) {
            if (user) {
                console.log(firebase.auth().currentUser);
                //Successful Login, go to Profile Page
                document.location.href="profile.html";
            } else {
                console.log("Failed to Sign in");
            }
        });
    });

    console.log("Creating Signup");
    btnSignup.addEventListener('click', e=> {
        //Get email and pass
        const email = txtEmail.value;
        const pass = txtPassword.value;
        const auth = firebase.auth();
        //Creating account
        const promise = auth.createUserWithEmailAndPassword(email,pass);
        promise.catch(e => console.log(e.message));
        console.log("Account Created");
    });
    
    console.log("Creating Logout");
    btnLogout.addEventListener('click', e=> {
       //Logout
        const auth = firebase.auth();
        const promise = auth.signOut();
        promise.catch(e => console.log(e.message));
        console.log("Logged Out");
    });
    
}());