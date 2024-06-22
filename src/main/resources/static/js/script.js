console.log("script loaded")

let currentTheme= getTheme()
console.log(currentTheme)
document.addEventListener('DOMContentLoaded', (event) => {
changeTheme()
});

function changeTheme(){
    // Set theme to the webpage
    document.querySelector('html').classList.add(currentTheme);

    // Set the listener to change theme button
    const changeThemeButton = document.getElementById("theme_toggle");
    console.log(changeThemeButton);

    changeThemeButton.addEventListener('click', (event) => {
        console.log("change theme button clicked");
        document.querySelector("html").classList.remove(currentTheme);
        if (currentTheme == "dark") {
            currentTheme = "light";
        } else {
            currentTheme = "dark";
        }
        // Change in localStorage
        setTheme(currentTheme);
        document.querySelector("html").classList.add(currentTheme);
    });

}


//set theme
function setTheme(theme){
    if(theme=="light")
    document.getElementById("current_theme").textContent="Light";
    else{
        document.getElementById("current_theme").textContent="Dark";

    }
    localStorage.setItem("theme",theme);
}

//get theme
function getTheme(){
    let theme=localStorage.getItem("theme")
    if(theme)
        return theme;
     else
        return "light"
}