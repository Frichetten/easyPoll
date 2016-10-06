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
        console.log(firebase.auth().currentUser.email);
        document.location.href = "profile.html";
    });

console.log("Creating signup");
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
    firebase.auth().onAuthStateChanged(firebaseUser => {
        if (firebaseUser) {
            console.log(firebaseUser);
            btnLogout.classList.remove('hide');
        } else {
            console.log('not logged in');
            // btnLogout.classList.add('hide');
        }
    });
    
}());