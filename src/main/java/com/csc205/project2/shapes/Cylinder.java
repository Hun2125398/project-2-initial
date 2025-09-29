package com.csc205.project2.shapes;

/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4 - September 28, 2025
 * Generation Date: September 28, 2025
 * 
 * Original Prompt:
 * "Generate a Cylinder class that extends Shape3D with properties: radius, height. Include proper constructors with validation, implement abstract calculation methods, override toString() with shape-specific formatting."
 * 
 * Follow-up Prompts (if any):
 * (None required)
 * 
 * Manual Modifications:
 * (To be filled in by developer if changes are made)
 * 
 * Formula Verification:
 * - Volume formula verified against: V = πr²h
 * - Surface area formula verified against: SA = 2πr² + 2πrh
 */
public class Cylinder extends Shape3D {
    private double radius;
    private double height;
    
    /**
     * Default constructor
     */
    public Cylinder() {
        super("Cylinder", "Unknown Color");
        this.radius = 1.0;
        this.height = 1.0;
    }
    
    /**
     * Constructor with radius and height
     * @param radius The radius of the cylinder base
     * @param height The height of the cylinder
     * @throws IllegalArgumentException if radius or height is negative or zero
     */
    public Cylinder(double radius, double height) {
        super("Cylinder", "Unknown Color");
        validateDimensions(radius, height);
        this.radius = radius;
        this.height = height;
    }
    
    /**
     * Constructor with name, color, radius, and height
     * @param name The name of the cylinder
     * @param color The color of the cylinder
     * @param radius The radius of the cylinder base
     * @param height The height of the cylinder
     * @throws IllegalArgumentException if radius or height is negative or zero
     */
    public Cylinder(String name, String color, double radius, double height) {
        super(name, color);
        validateDimensions(radius, height);
        this.radius = radius;
        this.height = height;
    }
    
    /**
     * Validates radius and height inputs
     * @param radius The radius to validate
     * @param height The height to validate
     * @throws IllegalArgumentException if radius or height is negative or zero
     */
    private void validateDimensions(double radius, double height) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive and greater than zero");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive and greater than zero");
        }
    }
    
    /**
     * Gets the radius of the cylinder
     * @return The radius
     */
    public double getRadius() {
        return radius;
    }
    
    /**
     * Sets the radius of the cylinder
     * @param radius The new radius
     * @throws IllegalArgumentException if radius is negative or zero
     */
    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive and greater than zero");
        }
        this.radius = radius;
    }
    
    /**
     * Gets the height of the cylinder
     * @return The height
     */
    public double getHeight() {
        return height;
    }
    
    /**
     * Sets the height of the cylinder
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
     * Calculates the volume of the cylinder
     * Formula: V = πr²h
     * @return The volume
     */
    @Override
    protected double calculateVolume() {
        return Math.PI * Math.pow(radius, 2) * height;
    }
    
    /**
     * Calculates the surface area of the cylinder
     * Formula: SA = 2πr² + 2πrh (2 circular bases + curved surface)
     * @return The surface area
     */
    @Override
    protected double calculateSurfaceArea() {
        return 2 * Math.PI * Math.pow(radius, 2) + 2 * Math.PI * radius * height;
    }
    
    /**
     * Returns a string representation of the cylinder
     * @return Formatted string with cylinder information
     */
    @Override
    public String toString() {
        return String.format("%s [Color: %s, Radius: %.2f, Height: %.2f, Volume: %.2f, Surface Area: %.2f]", 
                           getName(), getColor(), radius, height, getVolume(), getSurfaceArea());
    }
    
    /**
     * Gets the diameter of the cylinder base
     * @return The diameter (2 * radius)
     */
    public double getDiameter() {
        return 2 * radius;
    }
    
    /**
     * Gets the lateral (curved) surface area of the cylinder
     * @return The lateral surface area (2πrh)
     */
    public double getLateralSurfaceArea() {
        return 2 * Math.PI * radius * height;
    }
    
    /**
     * Gets the base area of the cylinder
     * @return The base area (πr²)
     */
    public double getBaseArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}