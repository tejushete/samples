package com.example.shopshrey;



public class shopShreyConstants {
    public final static String serverIp = "192.168.43.243";//ipconfig
    public final static String fashionURL = "http://"+serverIp+":8080/ShopShrey/test?category=Fashion&subCategory=";
    public final static String menURL = fashionURL+"Men";
    public final static String womenURL = fashionURL+"Women";
    public final static String musicalURL = "http://"+serverIp+":8080/ShopShrey/test?category=Musical&subCategory=Musical";
    public final static String electronicsURL = "http://"+serverIp+":8080/ShopShrey/test?category=Electronics&subCategory=";
    public final static String mobilesURL = electronicsURL + "Mobiles";
    public final static String smartWatchUrl = electronicsURL + "SmartWatch";
    public final static String tvUrl = electronicsURL + "TV";
    public final static String laptopUrl = electronicsURL + "Laptop";

    public final static String furnitureURL = "http://"+serverIp+":8080/ShopShrey/test?category=HomeAndFurnitures&subCategory=";
    public final static String homeDecorURL = furnitureURL + "HomeDecor";
    public final static String homeCleanURL = furnitureURL+ "HomeClean";
    public final static String kitchenAndDiningURL = furnitureURL + "KitchenAndDining";
    public final static String homeFurnishingURL = furnitureURL + "HomeFurnish";
    public final static String toolsAndHardwareURL = furnitureURL + "ToolsAndHardware";

    public final static String stationaryURL = "http://"+serverIp+":8080/ShopShrey/test?category=Stationary&subCategory=";
    public final static String booksURL = stationaryURL + "Books";
    public final static String otherURL = stationaryURL+ "Others";



    //add for other URL's also
}

