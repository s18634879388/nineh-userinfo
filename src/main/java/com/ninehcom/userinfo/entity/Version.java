/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.userinfo.entity;

/**
 *
 * @author Shenjizhe
 */
public class Version {

    private int[] SubVer;

    public int[] getSubVer() {
        return SubVer;
    }

    private Version() {

    }

    private Version(int[] SubVer) {
        this.SubVer = SubVer;
    }

    public static Version createVersion(String version) {
        if (version != null) {
            String[] subvers = version.split("\\.");
            if (subvers != null && subvers.length == 4) {
                try {
                    int[] subVerValue = new int[4];
                    for (int i = 0; i < 4; i++) {
                        subVerValue[i] = Integer.parseInt(subvers[i]);
                    }
                    return new Version(subVerValue);
                } catch (Exception ex) {

                }
            }
        }
        return null;
    }

    public static int compare(Version ver1, Version ver2) {
        for (int i = 0; i < 4; i++) {
            if( ver1.SubVer[i] > ver2.SubVer[i]){
                return 1;
            }else if( ver1.SubVer[i] < ver2.SubVer[i]){
                return -1;
            }
        }
        return 0;
    }
}
