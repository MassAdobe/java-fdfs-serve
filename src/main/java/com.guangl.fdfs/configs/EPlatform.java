package com.guangl.fdfs.configs;

/**
 * @ClassName: EPlatform
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-13 11:56
 * @Version: 1.0.0
 * @param: * @param null
 */
public enum EPlatform {
    Any("any"),
    Linux("Linux"),
    Mac_OS("Mac OS"),
    Mac_OS_X("Mac OS X"),
    Windows("Windows"),
    OS2("OS/2"),
    Solaris("Solaris"),
    SunOS("SunOS"),
    MPEiX("MPE/iX"),
    HP_UX("HP-UX"),
    AIX("AIX"),
    OS390("OS/390"),
    FreeBSD("FreeBSD"),
    Irix("Irix"),
    Digital_Unix("Digital Unix"),
    NetWare_411("NetWare"),
    OSF1("OSF1"),
    OpenVMS("OpenVMS"),
    Others("Others");

    private EPlatform(String desc) {
        this.description = desc;
    }

    public String toString() {
        return description;
    }

    private String description;
}
