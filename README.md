# AppProtectionService
**Security of applications against unauthorised explotation and copying.**

This installer requests a user's folder to set up there our program incorporated in the installer.

Afterwards it writes our embedded program into choosen path.

Then our app gets an information about current computer where we have installed the program, makes hash of this gained info and, as a result, creates a **signature** with this hash in the Windows registry in the directory: _HKEY_CURRENT_USER\Software\JetBrains\IntelliJ IDEA. 

Value name of this key is Roman_Horilyi (can be easily changed without any consequences).

**IMPORTANT:** Prerequisite of correct rights validation is to add to validator. In our case we work with project [AuthentiocationSystem] (https://github.com/RomarioGorilyi/AuthenticationSystem).
So we have added [Siganture.java] (https://github.com/RomarioGorilyi/AuthenticationSystem/blob/master/src/main/java/ua/ipt/signature/Signature.java) and [AuthenticationManager.java] (https://github.com/RomarioGorilyi/AuthenticationSystem/blob/master/src/main/java/ua/ipt/main/AuthenticationManager.java) which provide check of the current signature of the program with the signature from the registry.
