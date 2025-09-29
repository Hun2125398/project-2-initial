package com.csc205.project2.shapes;

/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4 - September 28, 2025
 * Generation Date: September 28, 2025
 * 
 * Original Prompt:
 * "Generate a RectangularPrism class that extends Shape3D with properties: length, width, height. Include proper constructors with validation, implement abstract calculation methods, override toString() with shape-specific formatting."
 * 
 * Follow-up Prompts (if any):
 * (None required)
 * 
 * Manual Modifications:
 * (To be filled in by developer if changes are made)
 * 
 * Formula Verification:
 * - Volume formula verified against: V = l × w × h
 * - Surface area formula verified against: SA = 2(lw + lh + wh)
 */
public class RectangularPrism extends Shape3D {
    private double length;
    private double width;
    private double height;
    
    /**
     * Default constructor
     */
    public RectangularPrism() {
        super("Rectangular Prism", "Unknown Color");
        this.length = 1.0;
        this.width = 1.0;
        this.height = 1.0;
    }
    
    /**
     * Constructor with length, width, and height
     * @param length The length of the rectangular prism
     * @param width The width of the rectangular prism
     * @param height The height of the rectangular prism
     * @throws IllegalArgumentException if any dimension is negative or zero
     */
    public RectangularPrism(double length, double width, double height) {
        super("Rectangular Prism", "Unknown Color");
        validateDimensions(length, width, height);
        this.length = length;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Constructor with name, color, length, width, and height
     * @param name The name of the rectangular prism
     * @param color The color of the rectangular prism
     * @param length The length of the rectangular prism
     * @param width The width of the rectangular prism
     * @param height The height of the rectangular prism
     * @throws IllegalArgumentException if any dimension is negative or zero
     */
    public RectangularPrism(String name, String color, double length, double width, double height) {
        super(name, color);
        validateDimensions(length, width, height);
        this.length = length;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Validates dimension inputs
     * @param length The length to validate
     * @param width The width to validate
     * @param height The height to validate
     * @throws IllegalArgumentException if any dimension is negative or zero
     */
    private void validateDimensions(double length, double width, double height) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive and greater than zero");
        }
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be positive and greater than zero");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive and greater than zero");
        }
    }
    
    /**
     * Gets the length of the rectangular prism
     * @return The length
     */
    public double getLength() {
        return length;
    }
    
    /**
     * Sets the length of the rectangular prism
     * @param length The new length
     * @throws IllegalArgumentException if length is negative or zero
     */
    public void setLength(double length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive and greater than zero");
        }
        this.length = length;
    }
    
    /**
     * Gets the width of the rectangular prism
     * @return The width
     */
    public double getWidth() {
        return width;
    }
    
    /**
     * Sets the width of the rectangular prism
     * @param width The new width
     * @throws IllegalArgumentException if width is negative or zero
     */
    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be positive and greater than zero");
        }
        this.width = width;
    }
    
    /**
     * Gets the height of the rectangular prism
     * @return The height
     */
    public double getHeight() {
        return height;
    }
    
    /**
     * Sets the height of the rectangular prism
     * @param height The new height
     * @throws IllegalArgumentException if height is negative or zero
     */
    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive and greater than zero");
        }
        this.height = height;
    }
    
    /**
     * Calculates the volume of the rectangular prism
     * Formula: V = l × w × h
     * @return The volume
     */
    @Override
    protected double calculateVolume() {
        return length * width * height;
    }
    
    /**
     * Calculates the surface area of the rectangular prism
     * Formula: SA = 2(lw + lh + wh)
     * @return The surface area
     */
    @Override
    protected double calculateSurfaceArea() {
        return 2 * (length * width + length * height + width * height);
    }
    
    /**
     * Returns a string representation of the rectangular prism
     * @return Formatted string with rectangular prism information
     */
    @Override
    public String toString() {
        return String.format("%s [Color: %s, Length: %.2f, Width: %.2f, Height: %.2f, Volume: %.2f, Surface Area: %.2f]", 
                           getName(), getColor(), length, width, height, getVolume(), getSurfaceArea());
    }
    
    /**
     * Gets the space diagonal of the rectangular prism
     * @return The space diagonal √(l² + w² + h²)
     */
    public double getSpaceDiagonal() {
        return Math.sqrt(Math.pow(length, 2) + Math.pow(width, 2) + Math.pow(height, 2));
    }
    
    /**
     * Checks if this is a cube (all dimensions equal)
     * @return true if length, width, and height are equal
     */
    public boolean isCube() {
        return Double.compare(length, width) == 0 && Double.compare(width, height) == 0;
    }
    
    /**
     * Gets the base area (length × width)
     * @return The base area
     */
    public double getBaseArea() {
        return length * width;
    }
}