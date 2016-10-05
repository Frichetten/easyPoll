(function () {

console.log("Initializing");


 console.log("Getting Elements");   
//Get elements
    const txtEmail = document.getElementById('txtEmail');
    const txtPassword = document.getElementById('txtPassword');
    const btnLogin = document.getElementById('btnLogin');
    const btnSignup = document.getElementById('btnSignup');
    const btnLogout = document.getElementById('btnLogout');
    
console.log("Creating login");    
//Add login event
    btnLogin.addEventListener('click', e => {
        //Get email and pass
        const email = txtEmail.value;
        const pass = txtPassword.value;
        const auth = firebase.auth();
        //sign in
        const promise = auth.signInWithEmailAndPassword(email,pass);
        console.log("Logging in");
        promise.catch(e => console.log(e.message));
        firebase.auth().onAuthStateChanged(function(user) {
            if (user) {
                console.log(firebase.auth().currentUser);
                document.location.href="profile.html";
            } else {
                // No user is signed in.
            }
        });
    });

console.log("Creating singup");
//Add signup event
    btnSignup.addEventListener('click', e=> {
          //Get email and pass
        const email = txtEmail.value;
        const pass = txtPassword.value;
        const auth = firebase.auth();
        //sign in
        const promise = auth.createUserWithEmailAndPassword(email,pass);
        promise.catch(e => console.log(e.message));
    });
    
console.log("Creating state listener");
//Add realtime listener
    btnLogout.addEventListener('click', e=> {
       //Logout
        const auth = firebase.auth();
        const promise = auth.signOut();
        promise.catch(e => console.log(e.message));
        console.log("logged out");
    });
    
}());