
function validateform(){  
var vfname=document.clientvalidate.fname.value;  
var vlname=document.clientvalidate.lname.value; 
var vuname=document.clientvalidate.uname.value; 
var vpword1=document.clientvalidate.pword.value; 
var vpword2=document.clientvalidate.rpword.value;
var vemail=document.clientvalidate.email.value; 
var mobile=document.clientvalidate.monbile.value; 
var atposition=vemail.indexOf("@");  
var dotposition=mobile.lastIndexOf(".");    
var mailformat="[a-zA-Z0-9]{1,}[@]{1}[a-zA-Z]{1,}.*";
if (vfname==null || vfname==""){
	alert("FirstName can't be blank");
	return false;
}
else if(vlname==null || vlname==""){
	alert("LastName can't be blank");
	return false;
}else if(vuname==null || vuname==""){
	alert("UName can't be blank");
	return false;
}

else if(vpword1==null || vpword1==""){
	alert("Password can't be blank");
	return false;
}
else if(vpword2==null || vpword2==""){
			alert("Re-enter Password field is mandatory");
			return false;
		}
else if(vpword1!=vpword2){  
	  alert("Password values are Not same ");  
	  return false;  
	  }else if(email==null || email==""){
			alert("Email field is required");
			return false;
		}
	  else if (atposition<1 || dotposition+2>=vemail.length){  
  alert("Please enter a valid e-mail address");  
  return false;  
  } else if(mobile==null || mobile==""){  
  alert("mobile can't be blank");  
  return false;  
} else if (mobile.length != 10) {
	alert("Enter 10 digit mobile number");
	return false;
}else if(vemail.match(mailformat))  {
	  alert('Register Details send for Admin approval!!!');
	  return true;
  } else{
	  alert("Enter the proper email address");  
	  return false;  
  } 
}     