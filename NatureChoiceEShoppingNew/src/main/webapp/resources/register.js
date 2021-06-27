function validateform()
{ 
var name=document.register.name.value;
var psw=document.register.psw.value;
var letters = /^[a-zA-Z0-9]+$/;
var cpass=document.register.cpass.value;
if(name.match(letters))
  {
    var name = document.getElementById('name').value;
  }
    else
  {
    alert('User name should be alphabet or numeric');

    return false;
  } 
  if(psw==null || psw.length<6){  
  alert("Password should not be blank and must be at least 6 characters long.");  
  return false;  
  }  
 
      else {

        var psw = document.getElementById('psw').value;

      }
  if (psw != cpass)
	{
		alert ("Password did not match");
         return false;
    }
  else {

        var cpass = document.getElementById('cpass').value;
    }
      }