package com.ynap.shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class YNAPShop {
	
    private final static char DEFAULT_SEPARATOR = ',';
    private final static char DEFAULT_QUOTE = '"';
    private List<String> line = new ArrayList<String>();
    private List<String> finalLine = new ArrayList<String>();
    private Hashtable<Integer, String> hashTable = new Hashtable<Integer, String>();
    private List<BigDecimal> pricesList = new ArrayList<BigDecimal>();
    
    private List<String> basket = new ArrayList<String>();

    /**
     * Load products from the .csv file
     * @throws FileNotFoundException 
     */
    public void loadProducts() throws FileNotFoundException {
        // TODO Exercise 1a - Parsing the product-data.csv data file
        String csvFile = "src/main/resources/product-data.csv";

        Scanner scanner = new Scanner(new File(csvFile));
        while (scanner.hasNext()) {
            line = parseLine(scanner.nextLine());
            finalLine.add(line.toString());
            //System.out.println("ProductID= " + line.get(0) + ", ProductName= " + line.get(1) + " , Price=" + line.get(2) + "]");
        }
        for(int i = 0; i < finalLine.size(); i++) {
        	if (finalLine.get(i).contains("#")) {
        		finalLine.remove(i);
        	}
        }
        scanner.close();
        if (line.size() < 1) {
        System.out.println("Load Products Not Implemented");
        }
    }

    /**
     * List available products
     * @throws FileNotFoundException 
     */
    public List<String> getProducts() throws FileNotFoundException {
        // TODO Exercise 1b - List products
    	
    	for (int i = 0; i < finalLine.size(); i++) {
    	//System.out.println("Line i= " + finalLine.get(i));
    	}
    	if (finalLine.size() < 1) {
    	System.out.println("List Products Not Implemented");
    	}
    	if (finalLine.size() < 1) {
    	return Collections.emptyList();
    	} else {
    		return finalLine;
    	}
    }
    


    /**
     * Display available products
     */
    public String displayProducts() {
        // TODO Exercise 1c - Display products
    	if (finalLine.size() < 1) {
        System.out.println("Display Products Not Implemented");
        return null;
    	} else {

    		StringBuffer sb = new StringBuffer();
    		for (int i = 0; i < finalLine.size(); i++) {
    			 sb.append(finalLine.get(i));
    	         sb.append("\n");
    		}
    	      String str = sb.toString();
    	      //System.out.println(str);
    		return str;
    	}
    }

    /**
     * Add a product to the Basket
     */
    public void addProductToBasket(String productId) {
        // TODO Exercise 2a - Add products to the basket
    	for (int i = 0; i < finalLine.size(); i++) {
    		hashTable.put(i+1, finalLine.get(i).toString());		
    	}

    	System.out.println("hashMap= " + hashTable);
    	if (hashTable.size() < 1) {
        System.out.println("Add Product Not Implemented");
    	} else {
    	basket.add(hashTable.get(Integer.parseInt(productId)));
    	//System.out.println("hashTable.get("+ productId +")= " + hashTable.get(productId) + " productId= " + productId + " Basket= " + basket);  
    	}
    }

    /**
     * Get the items in the basket
     */
    public List getBasketItems(){
        // TODO Exercise 2a - Add products to the basket
    	if (hashTable.size() < 1) {
        System.out.println("Get Basket Items Not Implemented");
        return Collections.emptyList();
    	} else {   		
    		System.out.println("Basket= " + basket);
    		return basket;
    	}
    }

    /**
     * Remove a product from the Basket
     */
    public void removeProductFromBasket(String productId) {
        // TODO Exercise 2b - Remove products from the basket
    	if (basket.size() < 0) {
        System.out.println("Remove Product Not Implemented");
    	} else {
    		basket.remove(Integer.parseInt(productId));
    	}
    }

    /**
     * Return the total value of the products in the basket
     */
    public BigDecimal getTotal() {
        // TODO Exercise 2c - Show the total value of products in the basket
    	BigDecimal bigDec = new BigDecimal(0);
    	BigDecimal finalBigDec = new BigDecimal(0);
    	if (basket.size() < 0) {
        System.out.println("Get Total Not Implemented");
        return BigDecimal.ZERO;
    	} else {
    		String[] tempArray;
    		for (int i = 0; i < basket.size(); i++) {
    			tempArray = basket.get(i).toString().split(", ");
    			
    			//System.out.println(tempArray[2].replace("£", "").replace("]", ""));
    			bigDec = new BigDecimal(Double.parseDouble(tempArray[2].replace("£", "").replace("]", "")));
    			
    			pricesList.add(bigDec);	
    			
    		}
    		bigDec = pricesList.get(0);
    		
    		for(int j = 0; j < pricesList.size()-1; j++) {  
    			System.out.println("finalBigDec= " + finalBigDec + " bigDec= " + bigDec + " pricesList.get(j+1)= " + pricesList.get(j+1));
    			finalBigDec = bigDec.add(pricesList.get(j+1));
    			bigDec = finalBigDec;
    			//System.out.println("finalBigDec= " + finalBigDec + " bigDec= " + bigDec);
    			//System.out.println("pricesList.get(j)= " + pricesList.get(j) + " pricesList.get(j+1)= " + pricesList.get(j+1));			
    		}
    		  		
    		finalBigDec = finalBigDec.setScale(2, BigDecimal.ROUND_DOWN);
    		return finalBigDec;
    	}
    }

    public static void main(String args[]){
        System.out.println("YNAP Shop. This application should be exercised by using the YNAPShopTest class.");
    }
    
    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());
        return result;
    }
    
    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators) {
        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
    }

}
