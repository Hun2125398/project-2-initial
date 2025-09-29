package com.csc205.project2.shapes;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Sophisticated 3D Shape Analysis System
 * Demonstrates polymorphism, comparative analysis, interactive features,
 * performance timing, and professional formatted output
 */
public class ShapeDriver {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Shape3D> shapes = new ArrayList<>();
    private static final String SEPARATOR = "=".repeat(60);
    private static final String SUBSEPARATOR = "-".repeat(40);
    
    public static void main(String[] args) {
        printWelcomeHeader();
        
        // Demonstrate predefined shapes with polymorphism
        demonstratePolymorphism();
        
        // Interactive shape creation
        interactiveShapeCreation();
        
        // Comprehensive analysis
        performComprehensiveAnalysis();
        
        // Performance timing demonstration
        performanceAnalysis();
        
        printClosingMessage();
    }
    
    /**
     * Prints welcome header with system information
     */
    private static void printWelcomeHeader() {
        System.out.println(SEPARATOR);
        System.out.println("              3D SHAPE ANALYSIS SYSTEM");
        System.out.println("         Advanced Polymorphic Geometry Tool");
        System.out.println(SEPARATOR);
        System.out.println("Features: Polymorphism • Analysis • Timing • Interaction");
        System.out.println();
    }
    
    /**
     * Demonstrates polymorphism with predefined shapes
     */
    private static void demonstratePolymorphism() {
        System.out.println(">>> POLYMORPHISM DEMONSTRATION");
        System.out.println();
        
        // Create array of Shape3D references holding different concrete types
        Shape3D[] polymorphicShapes = {
            new Sphere("Red Ball", "Crimson", 5.0),
            new Cube("Blue Box", "Navy", 4.0),
            new Cylinder("Green Tube", "Forest", 3.0, 6.0),
            new RectangularPrism("Yellow Block", "Gold", 2.0, 4.0, 3.0),
            new Cone("Purple Peak", "Violet", 4.0, 8.0)
        };
        
        // Add to main collection
        Collections.addAll(shapes, polymorphicShapes);
        
        System.out.println("Created " + polymorphicShapes.length + " shapes using polymorphic references:");
        System.out.println();
        
        // Demonstrate polymorphic method calls
        for (int i = 0; i < polymorphicShapes.length; i++) {
            Shape3D shape = polymorphicShapes[i];
            System.out.printf("%d. %s {type=%s, color=%s}%n", 
                i + 1, 
                shape.getName(), 
                shape.getClass().getSimpleName(), 
                shape.getColor());
            
            System.out.printf("   - Volume: %.2f cubic units%n", shape.getVolume());
            System.out.printf("   - Surface Area: %.2f square units%n", shape.getSurfaceArea());
            System.out.println();
        }
        
        waitForUser();
    }
    
    /**
     * Interactive shape creation allowing user customization
     */
    private static void interactiveShapeCreation() {
        System.out.println(">>> INTERACTIVE SHAPE CREATION");
        System.out.println();
        
        boolean continueCreating = true;
        while (continueCreating) {
            displayShapeMenu();
            int choice = getIntInput("Select shape type (1-6): ", 1, 6);
            
            if (choice == 6) {
                continueCreating = false;
                continue;
            }
            
            try {
                Shape3D newShape = createShapeFromInput(choice);
                if (newShape != null) {
                    shapes.add(newShape);
                    System.out.println("✓ Successfully created: " + newShape.getName());
                    System.out.printf("  Volume: %.2f | Surface Area: %.2f%n%n", 
                        newShape.getVolume(), newShape.getSurfaceArea());
                }
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Error: " + e.getMessage());
                System.out.println();
            }
        }
    }
    
    /**
     * Displays menu for shape selection
     */
    private static void displayShapeMenu() {
        System.out.println("Available Shape Types:");
        System.out.println("1. Sphere");
        System.out.println("2. Cube");
        System.out.println("3. Cylinder");
        System.out.println("4. Rectangular Prism");
        System.out.println("5. Cone");
        System.out.println("6. Finish creating shapes");
        System.out.println();
    }
    
    /**
     * Creates a shape based on user input
     */
    private static Shape3D createShapeFromInput(int choice) {
        System.out.println(SUBSEPARATOR);
        
        String name = getStringInput("Enter shape name: ");
        String color = getStringInput("Enter color: ");
        
        switch (choice) {
            case 1: // Sphere
                double radius = getDoubleInput("Enter radius: ", 0.001, Double.MAX_VALUE);
                return new Sphere(name, color, radius);
                
            case 2: // Cube
                double sideLength = getDoubleInput("Enter side length: ", 0.001, Double.MAX_VALUE);
                return new Cube(name, color, sideLength);
                
            case 3: // Cylinder
                double cylRadius = getDoubleInput("Enter radius: ", 0.001, Double.MAX_VALUE);
                double height = getDoubleInput("Enter height: ", 0.001, Double.MAX_VALUE);
                return new Cylinder(name, color, cylRadius, height);
                
            case 4: // Rectangular Prism
                double length = getDoubleInput("Enter length: ", 0.001, Double.MAX_VALUE);
                double width = getDoubleInput("Enter width: ", 0.001, Double.MAX_VALUE);
                double prismHeight = getDoubleInput("Enter height: ", 0.001, Double.MAX_VALUE);
                return new RectangularPrism(name, color, length, width, prismHeight);
                
            case 5: // Cone
                double coneRadius = getDoubleInput("Enter radius: ", 0.001, Double.MAX_VALUE);
                double coneHeight = getDoubleInput("Enter height: ", 0.001, Double.MAX_VALUE);
                return new Cone(name, color, coneRadius, coneHeight);
                
            default:
                return null;
        }
    }
    
    /**
     * Performs comprehensive comparative analysis
     */
    private static void performComprehensiveAnalysis() {
        if (shapes.isEmpty()) {
            System.out.println("No shapes available for analysis.");
            return;
        }
        
        System.out.println(">>> COMPREHENSIVE ANALYSIS");
        System.out.println();
        
        // Display all shapes with detailed information
        displayAllShapes();
        
        // Comparative analysis
        performComparativeAnalysis();
        
        // Statistical analysis
        performStatisticalAnalysis();
        
        // Efficiency analysis
        performEfficiencyAnalysis();
        
        waitForUser();
    }
    
    /**
     * Displays all shapes in a formatted table
     */
    private static void displayAllShapes() {
        System.out.println("Created Shapes Summary:");
        System.out.println();
        System.out.printf("%-3s %-20s %-15s %-12s %-12s %-8s%n", 
            "#", "Name", "Type", "Volume", "Surface", "Ratio");
        System.out.println(SUBSEPARATOR + "--------------------");
        
        for (int i = 0; i < shapes.size(); i++) {
            Shape3D shape = shapes.get(i);
            double volume = shape.getVolume();
            double surfaceArea = shape.getSurfaceArea();
            double ratio = volume / surfaceArea;
            
            System.out.printf("%-3d %-20s %-15s %10.2f %10.2f %6.3f%n", 
                i + 1, 
                truncateString(shape.getName(), 19),
                shape.getClass().getSimpleName(),
                volume,
                surfaceArea,
                ratio);
        }
        System.out.println();
    }
    
    /**
     * Performs comparative analysis to find extremes
     */
    private static void performComparativeAnalysis() {
        System.out.println("Comparative Analysis Results:");
        System.out.println();
        
        // Find shapes with extreme values
        Shape3D largestVolume = shapes.stream()
            .max(Comparator.comparingDouble(Shape3D::getVolume))
            .orElse(null);
            
        Shape3D smallestVolume = shapes.stream()
            .min(Comparator.comparingDouble(Shape3D::getVolume))
            .orElse(null);
            
        Shape3D largestSurface = shapes.stream()
            .max(Comparator.comparingDouble(Shape3D::getSurfaceArea))
            .orElse(null);
            
        Shape3D smallestSurface = shapes.stream()
            .min(Comparator.comparingDouble(Shape3D::getSurfaceArea))
            .orElse(null);
        
        if (largestVolume != null) {
            System.out.printf("🏆 Largest Volume: %s (%.2f cubic units)%n", 
                largestVolume.getName(), largestVolume.getVolume());
            System.out.printf("🔽 Smallest Volume: %s (%.2f cubic units)%n", 
                smallestVolume.getName(), smallestVolume.getVolume());
            System.out.printf("📏 Largest Surface Area: %s (%.2f square units)%n", 
                largestSurface.getName(), largestSurface.getSurfaceArea());
            System.out.printf("📐 Smallest Surface Area: %s (%.2f square units)%n", 
                smallestSurface.getName(), smallestSurface.getSurfaceArea());
        }
        System.out.println();
    }
    
    /**
     * Performs statistical analysis on the shape collection
     */
    private static void performStatisticalAnalysis() {
        System.out.println("Statistical Analysis:");
        System.out.println();
        
        DoubleSummaryStatistics volumeStats = shapes.stream()
            .mapToDouble(Shape3D::getVolume)
            .summaryStatistics();
            
        DoubleSummaryStatistics surfaceStats = shapes.stream()
            .mapToDouble(Shape3D::getSurfaceArea)
            .summaryStatistics();
        
        System.out.printf("Volume Statistics:%n");
        System.out.printf("  Average: %.2f | Total: %.2f%n", volumeStats.getAverage(), volumeStats.getSum());
        System.out.printf("  Range: %.2f - %.2f%n", volumeStats.getMin(), volumeStats.getMax());
        System.out.println();
        
        System.out.printf("Surface Area Statistics:%n");
        System.out.printf("  Average: %.2f | Total: %.2f%n", surfaceStats.getAverage(), surfaceStats.getSum());
        System.out.printf("  Range: %.2f - %.2f%n", surfaceStats.getMin(), surfaceStats.getMax());
        System.out.println();
        
        // Shape type distribution
        Map<String, Long> typeDistribution = shapes.stream()
            .collect(Collectors.groupingBy(
                shape -> shape.getClass().getSimpleName(),
                Collectors.counting()));
        
        System.out.println("Shape Type Distribution:");
        typeDistribution.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .forEach(entry -> System.out.printf("  %s: %d shapes%n", entry.getKey(), entry.getValue()));
        System.out.println();
    }
    
    /**
     * Performs efficiency analysis (volume to surface area ratio)
     */
    private static void performEfficiencyAnalysis() {
        System.out.println("Efficiency Analysis (Volume/Surface Ratio):");
        System.out.println();
        
        List<ShapeEfficiency> efficiencies = shapes.stream()
            .map(shape -> new ShapeEfficiency(shape, shape.getVolume() / shape.getSurfaceArea()))
            .sorted(Comparator.comparingDouble(ShapeEfficiency::getRatio).reversed())
            .collect(Collectors.toList());
        
        System.out.printf("%-3s %-20s %-15s %10s%n", "Rank", "Name", "Type", "Efficiency");
        System.out.println(SUBSEPARATOR + "----------");
        
        for (int i = 0; i < efficiencies.size(); i++) {
            ShapeEfficiency eff = efficiencies.get(i);
            System.out.printf("%-3d %-20s %-15s %10.4f%n", 
                i + 1,
                truncateString(eff.getShape().getName(), 19),
                eff.getShape().getClass().getSimpleName(),
                eff.getRatio());
        }
        System.out.println();
        
        if (!efficiencies.isEmpty()) {
            ShapeEfficiency mostEfficient = efficiencies.get(0);
            System.out.printf("⚡ Most Efficient Shape: %s (%.4f ratio)%n", 
                mostEfficient.getShape().getName(), mostEfficient.getRatio());
        }
        System.out.println();
    }
    
    /**
     * Demonstrates performance analysis with timing
     */
    private static void performanceAnalysis() {
        if (shapes.isEmpty()) {
            return;
        }
        
        System.out.println(">>> PERFORMANCE ANALYSIS");
        System.out.println();
        
        int iterations = 100000;
        System.out.printf("Measuring calculation performance (%,d iterations)...%n%n", iterations);
        
        // Warm up JVM
        for (Shape3D shape : shapes) {
            for (int i = 0; i < 1000; i++) {
                shape.getVolume();
                shape.getSurfaceArea();
            }
        }
        
        List<PerformanceResult> results = new ArrayList<>();
        
        for (Shape3D shape : shapes) {
            // Time volume calculations
            long startTime = System.nanoTime();
            for (int i = 0; i < iterations; i++) {
                shape.getVolume();
            }
            long volumeTime = System.nanoTime() - startTime;
            
            // Time surface area calculations
            startTime = System.nanoTime();
            for (int i = 0; i < iterations; i++) {
                shape.getSurfaceArea();
            }
            long surfaceTime = System.nanoTime() - startTime;
            
            results.add(new PerformanceResult(shape, volumeTime, surfaceTime));
        }
        
        // Display performance results
        System.out.printf("%-20s %-15s %12s %12s %12s%n", 
            "Shape", "Type", "Volume (ns)", "Surface (ns)", "Total (ns)");
        System.out.println(SUBSEPARATOR + "------------------------");
        
        for (PerformanceResult result : results) {
            System.out.printf("%-20s %-15s %12.0f %12.0f %12.0f%n",
                truncateString(result.getShape().getName(), 19),
                result.getShape().getClass().getSimpleName(),
                result.getVolumeTime() / (double) iterations,
                result.getSurfaceTime() / (double) iterations,
                (result.getVolumeTime() + result.getSurfaceTime()) / (double) iterations);
        }
        
        // Find fastest and slowest
        PerformanceResult fastest = results.stream()
            .min(Comparator.comparingLong(r -> r.getVolumeTime() + r.getSurfaceTime()))
            .orElse(null);
        PerformanceResult slowest = results.stream()
            .max(Comparator.comparingLong(r -> r.getVolumeTime() + r.getSurfaceTime()))
            .orElse(null);
        
        if (fastest != null && slowest != null) {
            System.out.println();
            System.out.printf("🚀 Fastest: %s (%s)%n", 
                fastest.getShape().getName(), 
                fastest.getShape().getClass().getSimpleName());
            System.out.printf("🐌 Slowest: %s (%s)%n", 
                slowest.getShape().getName(), 
                slowest.getShape().getClass().getSimpleName());
        }
        System.out.println();
    }
    
    // Helper Classes
    private static class ShapeEfficiency {
        private final Shape3D shape;
        private final double ratio;
        
        public ShapeEfficiency(Shape3D shape, double ratio) {
            this.shape = shape;
            this.ratio = ratio;
        }
        
        public Shape3D getShape() { return shape; }
        public double getRatio() { return ratio; }
    }
    
    private static class PerformanceResult {
        private final Shape3D shape;
        private final long volumeTime;
        private final long surfaceTime;
        
        public PerformanceResult(Shape3D shape, long volumeTime, long surfaceTime) {
            this.shape = shape;
            this.volumeTime = volumeTime;
            this.surfaceTime = surfaceTime;
        }
        
        public Shape3D getShape() { return shape; }
        public long getVolumeTime() { return volumeTime; }
        public long getSurfaceTime() { return surfaceTime; }
    }
    
    // Utility Methods
    private static String truncateString(String str, int maxLength) {
        return str.length() > maxLength ? str.substring(0, maxLength - 3) + "..." : str;
    }
    
    private static void waitForUser() {
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
        System.out.println();
    }
    
    private static int getIntInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Please enter a number between %d and %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    private static double getDoubleInput(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Please enter a number between %.3f and %.3f.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    private static String getStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Please enter a non-empty string.");
        }
    }
    
    private static void printClosingMessage() {
        System.out.println(SEPARATOR);
        System.out.println("            ANALYSIS COMPLETE");
        System.out.printf("        Processed %d shapes successfully%n", shapes.size());
        System.out.println("    Thank you for using the 3D Shape Analysis System!");
        System.out.println(SEPARATOR);
    }
}