# LKLib

Used in LASER KNIGHTS mod, namely Biome Makeover and an upcoming secret project.

There is currently some missing functionality, but this will largely work.

No guarantees on stability and is provided as-is.

## To use
In your build.gradle

Versions can be found here: https://github.com/LASERKNIGHTS/LASERKNIGHTS/tree/main/party/lemons/lklib

```groovy
repositories {
	maven {
        url = 'https://raw.githubusercontent.com/LASERKNIGHTS/LASERKNIGHTS/main/'
    }
}

dependencies {
    modImplementation group: 'party.lemons', name: 'lklib', version: 'LKLib VERSION'
    include group: 'party.lemons', name: 'lklib', version: 'LKLib VERSION'
}
```