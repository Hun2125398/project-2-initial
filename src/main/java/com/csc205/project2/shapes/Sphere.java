package com.csc205.project2.shapes;

/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4 - September 28, 2025
 * Generation Date: September 28, 2025
 * 
 * Original Prompt:
 * "Generate a Sphere class that extends Shape3D with properties: radius. Include proper constructors with validation, implement abstract calculation methods, override toString() with shape-specific formatting."
 * 
 * Follow-up Prompts (if any):
 * (None required)
 * 
 * Manual Modifications:
 * (To be filled in by developer if changes are made)
 * 
 * Formula Verification:
 * - Volume formula verified against: V = (4/3)πr³
 * - Surface area formula verified against: SA = 4πr²
 */
public class Sphere extends Shape3D {
    private double radius;
    
    /**
     * Default constructor
     */
    public Sphere() {
        super("Sphere", "Unknown Color");
        this.radius = 1.0;
    }
    
    /**
     * Constructor with radius
     * @param radius The radius of the sphere
     * @throws IllegalArgumentException if radius is negative or zero
     */
    public Sphere(double radius) {
        super("Sphere", "Unknown Color");
        validateRadius(radius);
        this.radius = radius;
    }
    
    /**
     * Constructor with name, color, and radius
     * @param name The name of the sphere
     * @param color The color of the sphere
     * @param radius The radius of the sphere
     * @throws IllegalArgumentException if radius is negative or zero
     */
    public Sphere(String name, String color, double radius) {
        super(name, color);
        validateRadius(radius);
        this.radius = radius;
    }
    
    /**
     * Validates radius input
     * @param radius The radius to validate
     * @throws IllegalArgumentException if radius is negative or zero
     */
    private void validateRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive and greater than zero");
        }
    }
    
    /**
     * Gets the radius of the sphere
     * @return The radius
     */
    public double getRadius() {
        return radius;
    }
    
    /**
     * Sets the radius of the sphere
     * @param radius The new radius
     * @throws IllegalArgumentException if radius is negative or zero
     */
    public void setRadius(double radius) {
        validateRadius(radius);
        this.radius = radius;
    }
    
    /**
     * Calculates the volume of the sphere
     * Formula: V = (4/3)πr³
     * @return The volume
     */
    @Override
    protected double calculateVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }
    
    /**
     * Calculates the surface area of the sphere
     * Formula: SA = 4πr²
     * @return The surface area
     */
    @Override
    protected double calculateSurfaceArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }
    
    /**
     * Returns a string representation of the sphere
     * @return Formatted string with sphere information
     */
    @Override
    public String toString() {
        return String.format("%s [Color: %s, Radius: %.2f, Volume: %.2f, Surface Area: %.2f]", 
                           getName(), getColor(), radius, getVolume(), getSurfaceArea());
    }
    
    /**
     * Gets the diameter of the sphere
     * @return The diameter (2 * radius)
     */
    public double getDiameter() {
        return 2 * radius;
    }
}