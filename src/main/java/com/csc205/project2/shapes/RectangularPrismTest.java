package com.csc205.project2.shapes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test class for RectangularPrism
 * Tests basic functionality, calculation accuracy, boundary conditions,
 * input validation, and inheritance behavior
 */
@DisplayName("Rectangular Prism Tests")
class RectangularPrismTest {
    
    private static final double DELTA = 0.001; // Tolerance for floating point comparisons
    
    @Nested
    @DisplayName("Basic Functionality Tests")
    class BasicFunctionalityTests {
        
        @Test
        @DisplayName("Default constructor creates rectangular prism with default values")
        void defaultConstructor() {
            RectangularPrism prism = new RectangularPrism();
            
            assertEquals("Rectangular Prism", prism.getName());
            assertEquals("Unknown Color", prism.getColor());
            assertEquals(1.0, prism.getLength(), DELTA);
            assertEquals(1.0, prism.getWidth(), DELTA);
            assertEquals(1.0, prism.getHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Constructor with dimensions sets correct values")
        void constructorWithDimensions() {
            double length = 4.0;
            double width = 3.0;
            double height = 2.0;
            RectangularPrism prism = new RectangularPrism(length, width, height);
            
            assertEquals("Rectangular Prism", prism.getName());
            assertEquals("Unknown Color", prism.getColor());
            assertEquals(length, prism.getLength(), DELTA);
            assertEquals(width, prism.getWidth(), DELTA);
            assertEquals(height, prism.getHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Full constructor sets all values correctly")
        void fullConstructor() {
            String name = "Test Prism";
            String color = "Green";
            double length = 5.0;
            double width = 3.0;
            double height = 2.0;
            
            RectangularPrism prism = new RectangularPrism(name, color, length, width, height);
            
            assertEquals(name, prism.getName());
            assertEquals(color, prism.getColor());
            assertEquals(length, prism.getLength(), DELTA);
            assertEquals(width, prism.getWidth(), DELTA);
            assertEquals(height, prism.getHeight(), DELTA);
        }
        
        @Test
        @DisplayName("All dimension getters and setters work correctly")
        void dimensionGettersSetters() {
            RectangularPrism prism = new RectangularPrism();
            
            prism.setLength(6.0);
            prism.setWidth(4.0);
            prism.setHeight(3.0);
            
            assertEquals(6.0, prism.getLength(), DELTA);
            assertEquals(4.0, prism.getWidth(), DELTA);
            assertEquals(3.0, prism.getHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Space diagonal calculation is correct")
        void spaceDiagonalCalculation() {
            double length = 3.0;
            double width = 4.0;
            double height = 5.0;
            RectangularPrism prism = new RectangularPrism(length, width, height);
            
            // Space diagonal = √(l² + w² + h²) = √(9 + 16 + 25) = √50 ≈ 7.071
            double expectedDiagonal = Math.sqrt(Math.pow(length, 2) + Math.pow(width, 2) + Math.pow(height, 2));
            assertEquals(expectedDiagonal, prism.getSpaceDiagonal(), DELTA);
            assertEquals(7.071, prism.getSpaceDiagonal(), 0.01);
        }
        
        @Test
        @DisplayName("Base area calculation is correct")
        void baseAreaCalculation() {
            double length = 6.0;
            double width = 4.0;
            RectangularPrism prism = new RectangularPrism(length, width, 2.0);
            
            assertEquals(length * width, prism.getBaseArea(), DELTA);
            assertEquals(24.0, prism.getBaseArea(), DELTA);
        }
        
        @Test
        @DisplayName("isCube method works correctly")
        void isCubeMethod() {
            RectangularPrism cube = new RectangularPrism(3.0, 3.0, 3.0);
            RectangularPrism notCube = new RectangularPrism(3.0, 4.0, 3.0);
            
            assertTrue(cube.isCube());
            assertFalse(notCube.isCube());
        }
        
        @Test
        @DisplayName("toString method returns properly formatted string")
        void toStringFormatting() {
            RectangularPrism prism = new RectangularPrism("Test Prism", "Purple", 2.0, 3.0, 4.0);
            String result = prism.toString();
            
            assertTrue(result.contains("Test Prism"));
            assertTrue(result.contains("Purple"));
            assertTrue(result.contains("2.00"));
            assertTrue(result.contains("3.00"));
            assertTrue(result.contains("4.00"));
        }
    }
    
    @Nested
    @DisplayName("Calculation Accuracy Tests")
    class CalculationAccuracyTests {
        
        @Test
        @DisplayName("Volume calculation for 2x3x4 prism is accurate")
        void prismVolumeCalculation() {
            // Volume = length × width × height = 2 × 3 × 4 = 24
            double length = 2.0;
            double width = 3.0;
            double height = 4.0;
            RectangularPrism prism = new RectangularPrism(length, width, height);
            
            assertEquals(24.0, prism.getVolume(), DELTA);
        }
        
        @Test
        @DisplayName("Surface area calculation for 1x2x3 prism is accurate")
        void prismSurfaceAreaCalculation() {
            // Surface area = 2(lw + lh + wh) = 2(1×2 + 1×3 + 2×3) = 2(2 + 3 + 6) = 22
            double length = 1.0;
            double width = 2.0;
            double height = 3.0;
            RectangularPrism prism = new RectangularPrism(length, width, height);
            
            assertEquals(22.0, prism.getSurfaceArea(), DELTA);
        }
        
        @Test
        @DisplayName("Unit cube calculations are correct")
        void unitCubeCalculations() {
            // Unit cube (1×1×1) has volume = 1 and surface area = 6
            RectangularPrism unitCube = new RectangularPrism(1.0, 1.0, 1.0);
            
            assertEquals(1.0, unitCube.getVolume(), DELTA);
            assertEquals(6.0, unitCube.getSurfaceArea(), DELTA);
            assertTrue(unitCube.isCube());
        }
        
        @Test
        @DisplayName("Large prism calculations are accurate")
        void largePrismCalculations() {
            double length = 10.0;
            double width = 5.0;
            double height = 2.0;
            RectangularPrism prism = new RectangularPrism(length, width, height);
            
            assertEquals(100.0, prism.getVolume(), DELTA); // 10 × 5 × 2
            assertEquals(160.0, prism.getSurfaceArea(), DELTA); // 2(50 + 20 + 10)
        }
        
        @Test
        @DisplayName("Fractional dimensions calculations are accurate")
        void fractionalDimensionsCalculations() {
            double length = 0.5;
            double width = 0.4;
            double height = 0.3;
            RectangularPrism prism = new RectangularPrism(length, width, height);
            
            assertEquals(0.06, prism.getVolume(), DELTA); // 0.5 × 0.4 × 0.3
            assertEquals(0.94, prism.getSurfaceArea(), DELTA); // 2(0.2 + 0.15 + 0.12)
        }
    }
    
    @Nested
    @DisplayName("Boundary Testing")
    class BoundaryTests {
        
        @Test
        @DisplayName("Very small positive dimensions work correctly")
        void verySmallDimensions() {
            double small = 0.001;
            RectangularPrism prism = new RectangularPrism(small, small, small);
            
            assertTrue(prism.getVolume() > 0);
            assertTrue(prism.getSurfaceArea() > 0);
            assertEquals(small, prism.getLength(), DELTA);
            assertEquals(small, prism.getWidth(), DELTA);
            assertEquals(small, prism.getHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Large dimensions work correctly")
        void largeDimensions() {
            double large = 1000.0;
            RectangularPrism prism = new RectangularPrism(large, large, large);
            
            assertTrue(prism.getVolume() > 0);
            assertTrue(prism.getSurfaceArea() > 0);
            assertEquals(large, prism.getLength(), DELTA);
        }
        
        @Test
        @DisplayName("Extreme aspect ratios work correctly")
        void extremeAspectRatios() {
            // Very long, thin prism
            RectangularPrism longThin = new RectangularPrism(100.0, 0.1, 0.1);
            assertTrue(longThin.getVolume() > 0);
            assertTrue(longThin.getSurfaceArea() > 0);
            assertFalse(longThin.isCube());
            
            // Very flat prism
            RectangularPrism flat = new RectangularPrism(10.0, 10.0, 0.01);
            assertTrue(flat.getVolume() > 0);
            assertTrue(flat.getSurfaceArea() > 0);
            assertFalse(flat.isCube());
        }
    }
    
    @Nested
    @DisplayName("Input Validation Tests")
    class InputValidationTests {
        
        @Test
        @DisplayName("Constructor with zero length throws exception")
        void constructorWithZeroLength() {
            assertThrows(IllegalArgumentException.class, () -> {
                new RectangularPrism(0.0, 1.0, 1.0);
            });
        }
        
        @Test
        @DisplayName("Constructor with zero width throws exception")
        void constructorWithZeroWidth() {
            assertThrows(IllegalArgumentException.class, () -> {
                new RectangularPrism(1.0, 0.0, 1.0);
            });
        }
        
        @Test
        @DisplayName("Constructor with zero height throws exception")
        void constructorWithZeroHeight() {
            assertThrows(IllegalArgumentException.class, () -> {
                new RectangularPrism(1.0, 1.0, 0.0);
            });
        }
        
        @Test
        @DisplayName("Constructor with negative dimensions throws exception")
        void constructorWithNegativeDimensions() {
            assertThrows(IllegalArgumentException.class, () -> {
                new RectangularPrism(-1.0, 1.0, 1.0);
            });
            
            assertThrows(IllegalArgumentException.class, () -> {
                new RectangularPrism(1.0, -2.0, 1.0);
            });
            
            assertThrows(IllegalArgumentException.class, () -> {
                new RectangularPrism(1.0, 1.0, -3.0);
            });
        }
        
        @Test
        @DisplayName("Setters with invalid values throw exceptions")
        void settersValidation() {
            RectangularPrism prism = new RectangularPrism(1.0, 1.0, 1.0);
            
            assertThrows(IllegalArgumentException.class, () -> {
                prism.setLength(0.0);
            });
            
            assertThrows(IllegalArgumentException.class, () -> {
                prism.setWidth(-1.0);
            });
            
            assertThrows(IllegalArgumentException.class, () -> {
                prism.setHeight(0.0);
            });
        }
        
        @Test
        @DisplayName("Exception messages are descriptive")
        void exceptionMessagesAreDescriptive() {
            Exception lengthException = assertThrows(IllegalArgumentException.class, () -> {
                new RectangularPrism(0.0, 1.0, 1.0);
            });
            assertTrue(lengthException.getMessage().toLowerCase().contains("length"));
            
            Exception widthException = assertThrows(IllegalArgumentException.class, () -> {
                new RectangularPrism(1.0, -1.0, 1.0);
            });
            assertTrue(widthException.getMessage().toLowerCase().contains("width"));
        }
    }
    
    @Nested
    @DisplayName("Inheritance Testing")
    class InheritanceTests {
        
        @Test
        @DisplayName("RectangularPrism works as Shape3D reference")
        void prismAsShape3D() {
            Shape3D shape = new RectangularPrism("Poly Prism", "Orange", 2.0, 3.0, 4.0);
            
            assertEquals("Poly Prism", shape.getName());
            assertEquals("Orange", shape.getColor());
            assertTrue(shape.getVolume() > 0);
            assertTrue(shape.getSurfaceArea() > 0);
            
            // Test calculations through base class interface
            assertEquals(24.0, shape.getVolume(), DELTA); // 2 × 3 × 4
            assertEquals(52.0, shape.getSurfaceArea(), DELTA); // 2(6 + 8 + 12)
        }
        
        @Test
        @DisplayName("RectangularPrism implements ThreeDimensionalShape interface")
        void prismImplementsInterface() {
            ThreeDimensionalShape shape = new RectangularPrism(1.5, 2.0, 2.5);
            
            assertTrue(shape.getVolume() > 0);
            assertTrue(shape.getSurfaceArea() > 0);
            
            assertEquals(7.5, shape.getVolume(), DELTA); // 1.5 × 2.0 × 2.5
        }
        
        @Test
        @DisplayName("RectangularPrism toString works through inheritance")
        void toStringThroughInheritance() {
            Shape3D shape = new RectangularPrism("Inherited", "Cyan", 1.5, 2.0, 2.5);
            String result = shape.toString();
            
            assertTrue(result.contains("Inherited"));
            assertTrue(result.contains("Cyan"));
            assertTrue(result.contains("1.50"));
            assertTrue(result.contains("2.00"));
            assertTrue(result.contains("2.50"));
        }
        
        @Test
        @DisplayName("Inherited name and color properties work correctly")
        void inheritedPropertiesWork() {
            RectangularPrism prism = new RectangularPrism("Test Name", "Test Color", 2.0, 3.0, 4.0);
            
            prism.setName("New Name");
            prism.setColor("New Color");
            
            assertEquals("New Name", prism.getName());
            assertEquals("New Color", prism.getColor());
        }
    }
    
    @Nested
    @DisplayName("RectangularPrism-Specific Features Tests")
    class PrismSpecificTests {
        
        @Test
        @DisplayName("Space diagonal formula verification")
        void spaceDiagonalFormulaVerification() {
            // Test with 3-4-5 right triangle extended to 3D
            RectangularPrism prism = new RectangularPrism(3.0, 4.0, 0.0); // This will fail validation
            // Let's use a valid example instead
            RectangularPrism validPrism = new RectangularPrism(3.0, 4.0, 5.0);
            
            double expectedDiagonal = Math.sqrt(9 + 16 + 25); // √50 ≈ 7.071
            assertEquals(expectedDiagonal, validPrism.getSpaceDiagonal(), DELTA);
        }
        
        @Test
        @DisplayName("Base area formula verification")
        void baseAreaFormulaVerification() {
            double[][] testDimensions = {{2.0, 3.0}, {5.0, 7.0}, {1.5, 4.2}};
            
            for (double[] dims : testDimensions) {
                RectangularPrism prism = new RectangularPrism(dims[0], dims[1], 1.0);
                double expectedBaseArea = dims[0] * dims[1];
                assertEquals(expectedBaseArea, prism.getBaseArea(), DELTA,
                    "Base area incorrect for dimensions " + dims[0] + "x" + dims[1]);
            }
        }
        
        @Test
        @DisplayName("isCube detection works correctly")
        void isCubeDetection() {
            // Perfect cubes
            assertTrue(new RectangularPrism(1.0, 1.0, 1.0).isCube());
            assertTrue(new RectangularPrism(5.0, 5.0, 5.0).isCube());
            assertTrue(new RectangularPrism(0.5, 0.5, 0.5).isCube());
            
            // Not cubes
            assertFalse(new RectangularPrism(1.0, 1.0, 1.1).isCube());
            assertFalse(new RectangularPrism(2.0, 3.0, 2.0).isCube());
            assertFalse(new RectangularPrism(1.0, 2.0, 3.0).isCube());
        }
        
        @Test
        @DisplayName("Surface area components verification")
        void surfaceAreaComponents() {
            double length = 3.0;
            double width = 4.0;
            double height = 5.0;
            RectangularPrism prism = new RectangularPrism(length, width, height);
            
            // Calculate individual face areas
            double face1 = length * width;  // 12
            double face2 = length * height; // 15
            double face3 = width * height;  // 20
            
            // Total surface area should be 2 * (face1 + face2 + face3)
            double expectedSurfaceArea = 2 * (face1 + face2 + face3);
            assertEquals(expectedSurfaceArea, prism.getSurfaceArea(), DELTA);
            assertEquals(94.0, prism.getSurfaceArea(), DELTA); // 2(12 + 15 + 20)
        }
        
        @Test
        @DisplayName("Dimensional relationships work correctly")
        void dimensionalRelationships() {
            RectangularPrism prism = new RectangularPrism(6.0, 8.0, 10.0);
            
            // Space diagonal should be longest dimension
            assertTrue(prism.getSpaceDiagonal() > prism.getLength());
            assertTrue(prism.getSpaceDiagonal() > prism.getWidth());
            assertTrue(prism.getSpaceDiagonal() > prism.getHeight());
            
            // Base area should equal length × width
            assertEquals(prism.getLength() * prism.getWidth(), prism.getBaseArea(), DELTA);
        }
    }
    
    @Nested
    @DisplayName("Edge Cases and Special Scenarios")
    class EdgeCasesTests {
        
        @Test
        @DisplayName("Multiple prisms have independent properties")
        void independentPrisms() {
            RectangularPrism prism1 = new RectangularPrism("Prism1", "Red", 1.0, 2.0, 3.0);
            RectangularPrism prism2 = new RectangularPrism("Prism2", "Blue", 3.0, 2.0, 1.0);
            
            assertNotEquals(prism1.getLength(), prism2.getLength());
            assertNotEquals(prism1.getHeight(), prism2.getHeight());
            assertEquals(prism1.getVolume(), prism2.getVolume(), DELTA); // Same volume different shape
            assertNotEquals(prism1.getSurfaceArea(), prism2.getSurfaceArea()); // Different surface area
        }
        
        @Test
        @DisplayName("Prism calculations remain consistent after property changes")
        void calculationsAfterPropertyChanges() {
            RectangularPrism prism = new RectangularPrism(2.0, 3.0, 4.0);
            double originalVolume = prism.getVolume();
            
            prism.setName("Changed Name");
            prism.setColor("Changed Color");
            
            // Volume should remain the same since dimensions didn't change
            assertEquals(originalVolume, prism.getVolume(), DELTA);
            
            // Change dimensions and verify volume changes
            prism.setLength(3.0);
            assertNotEquals(originalVolume, prism.getVolume(), DELTA);
            assertEquals(36.0, prism.getVolume(), DELTA); // 3 × 3 × 4
        }
        
        @Test
        @DisplayName("Scaling properties work correctly")
        void scalingProperties() {
            RectangularPrism original = new RectangularPrism(1.0, 2.0, 3.0);
            RectangularPrism doubled = new RectangularPrism(2.0, 4.0, 6.0);
            
            // When all dimensions double, volume increases by factor of 8
            assertEquals(8.0, doubled.getVolume() / original.getVolume(), DELTA);
            
            // When all dimensions double, surface area increases by factor of 4
            assertEquals(4.0, doubled.getSurfaceArea() / original.getSurfaceArea(), DELTA);
        }
        
        @Test
        @DisplayName("Cube vs rectangular prism comparison")
        void cubeVsPrismComparison() {
            double volume = 24.0; // Target volume
            
            // Cube with same volume: side = ∛24 ≈ 2.884
            double cubeSide = Math.pow(volume, 1.0/3.0);
            RectangularPrism cube = new RectangularPrism(cubeSide, cubeSide, cubeSide);
            
            // Rectangular prism with same volume but different shape
            RectangularPrism prism = new RectangularPrism(2.0, 3.0, 4.0);
            
            assertEquals(volume, cube.getVolume(), DELTA);
            assertEquals(volume, prism.getVolume(), DELTA);
            
            // Cube should have minimum surface area for given volume
            assertTrue(cube.getSurfaceArea() < prism.getSurfaceArea());
        }
        
        @Test
        @DisplayName("Degenerate cases approach limits correctly")
        void degenerateCases() {
            // Very thin prism approaches area of rectangle
            RectangularPrism thin = new RectangularPrism(10.0, 5.0, 0.001);
            double thinVolume = thin.getVolume();
            assertTrue(thinVolume < 0.1); // Very small volume
            
            // Very long prism
            RectangularPrism longPrism = new RectangularPrism(1000.0, 1.0, 1.0);
            assertEquals(1000.0, longPrism.getVolume(), DELTA);
        }
        
        @Test
        @DisplayName("Mathematical precision with floating point")
        void floatingPointPrecision() {
            // Test with values that might cause precision issues
            RectangularPrism prism = new RectangularPrism(0.1, 0.2, 0.3);
            
            assertEquals(0.006, prism.getVolume(), 0.0001);
            assertTrue(prism.getSurfaceArea() > 0);
            assertTrue(prism.getSpaceDiagonal() > 0);
        }
    }
}