[Setup]
AppName=Additional Thickness Calculator
AppVersion=1.0
DefaultDirName={pf}\AdditionalThicknessCalculator

[Files]
Source: "C:\Users\ADMIN\Documents\Java Projects\2ValCalc\app.jar"; DestDir: "{app}"

[Icons]
Name: "{group}\Additional Thickness Calculator"; Filename: "{app}\app.jar"

[Run]
Filename: "{app}\app.jar"; Description: "Launch Additional Thickness Calculator"; Flags: nowait postinstall skipifsilent
