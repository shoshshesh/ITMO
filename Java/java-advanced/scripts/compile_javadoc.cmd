cd ..
if not exist javadoc (mkdir javadoc)
cd javadoc
if not exist implementor (mkdir implementor)
cd implementor
SET implementorIn2022=..\..\..\java-advanced-2022\modules\info.kgeorgiy.java.advanced.implementor\info\kgeorgiy\java\advanced\implementor
javadoc -author -private ..\..\java-solutions\info\kgeorgiy\ja\bondarev\implementor\Implementor.java %implementorIn2022%\Impler.java %implementorIn2022%\JarImpler.java %implementorIn2022%\ImplerException.java