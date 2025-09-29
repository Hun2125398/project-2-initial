package com.csc205.project2.shapes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test class for Cube
 * Tests basic functionality, calculation accuracy, boundary conditions,
 * input validation, and inheritance behavior
 */
@DisplayName("Cube Tests")
class CubeTest {
    
    private static final double DELTA = 0.001; // Tolerance for floating point comparisons
    
    @Nested
    @DisplayName("Basic Functionality Tests")
    class BasicFunctionalityTests {
        
        @Test
        @DisplayName("Default constructor creates cube with default values")
        void defaultConstructor() {
            Cube cube = new Cube();
            
            assertEquals("Cube", cube.getName());
            assertEquals("Unknown Color", cube.getColor());
            assertEquals(1.0, cube.getSideLength(), DELTA);
        }
        
        @Test
        @DisplayName("Constructor with side length sets correct values")
        void constructorWithSideLength() {
            double sideLength = 5.0;
            Cube cube = new Cube(sideLength);
            
            assertEquals("Cube", cube.getName());
            assertEquals("Unknown Color", cube.getColor());
            assertEquals(sideLength, cube.getSideLength(), DELTA);
        }
        
        @Test
        @DisplayName("Full constructor sets all values correctly")
        void fullConstructor() {
            String name = "Test Cube";
            String color = "Red";
            double sideLength = 3.5;
            
            Cube cube = new Cube(name, color, sideLength);
            
            assertEquals(name, cube.getName());
            assertEquals(color, cube.getColor());
            assertEquals(sideLength, cube.getSideLength(), DELTA);
        }
        
        @Test
        @DisplayName("Side length getter and setter work correctly")
        void sideLengthGetterSetter() {
            Cube cube = new Cube();
            double newSideLength = 7.5;
            
            cube.setSideLength(newSideLength);
            assertEquals(newSideLength, cube.getSideLength(), DELTA);
        }
        
        @Test
        @DisplayName("Face diagonal calculation is correct")
        void faceDiagonalCalculation() {
            double sideLength = 3.0;
            Cube cube = new Cube(sideLength);
            double expectedFaceDiagonal = sideLength * Math.sqrt(2);
            
            assertEquals(expectedFaceDiagonal, cube.getFaceDiagonal(), DELTA);
        }
        
        @Test
        @DisplayName("Space diagonal calculation is correct")
        void spaceDiagonalCalculation() {
            double sideLength = 4.0;
            Cube cube = new Cube(sideLength);
            double expectedSpaceDiagonal = sideLength * Math.sqrt(3);
            
            assertEquals(expectedSpaceDiagonal, cube.getSpaceDiagonal(), DELTA);
        }
        
        @Test
        @DisplayName("toString method returns properly formatted string")
        void toStringFormatting() {
            Cube cube = new Cube("Test Cube", "Blue", 2.0);
            String result = cube.toString();
            
            assertTrue(result.contains("Test Cube"));
            assertTrue(result.contains("Blue"));
            assertTrue(result.contains("2.00"));
        }
    }
    
    @Nested
    @DisplayName("Calculation Accuracy Tests")
    class CalculationAccuracyTests {
        
        @Test
        @DisplayName("Volume calculation for side length 3 is accurate")
        void cubeVolumeCalculation() {
            // Volume of cube with side length 3 should be 3³ = 27
            double sideLength = 3.0;
            Cube cube = new Cube(sideLength);
            double expectedVolume = Math.pow(sideLength, 3);
            
            assertEquals(expectedVolume, cube.getVolume(), DELTA);
            assertEquals(27.0, cube.getVolume(), DELTA);
        }
        
        @Test
        @DisplayName("Surface area calculation for side length 2 is accurate")
        void cubeSurfaceAreaCalculation() {
            // Surface area of cube with side length 2 should be 6 * 2² = 24
            double sideLength = 2.0;
            Cube cube = new Cube(sideLength);
            double expectedSurfaceArea = 6 * Math.pow(sideLength, 2);
            
            assertEquals(expectedSurfaceArea, cube.getSurfaceArea(), DELTA);
            assertEquals(24.0, cube.getSurfaceArea(), DELTA);
        }
        
        @Test
        @DisplayName("Unit cube calculations are correct")
        void unitCubeCalculations() {
            // Unit cube (side length = 1) has volume = 1 and surface area = 6
            Cube unitCube = new Cube(1.0);
            
            assertEquals(1.0, unitCube.getVolume(), DELTA);
            assertEquals(6.0, unitCube.getSurfaceArea(), DELTA);
        }
        
        @Test
        @DisplayName("Large side length calculations are accurate")
        void largeSideLengthCalculations() {
            double sideLength = 10.0;
            Cube cube = new Cube(sideLength);
            
            assertEquals(1000.0, cube.getVolume(), DELTA); // 10³
            assertEquals(600.0, cube.getSurfaceArea(), DELTA); // 6 * 10²
        }
        
        @Test
        @DisplayName("Fractional side length calculations are accurate")
        void fractionalSideLengthCalculations() {
            double sideLength = 0.5;
            Cube cube = new Cube(sideLength);
            
            assertEquals(0.125, cube.getVolume(), DELTA); // 0.5³
            assertEquals(1.5, cube.getSurfaceArea(), DELTA); // 6 * 0.5²
        }
    }
    
    @Nested
    @DisplayName("Boundary Testing")
    class BoundaryTests {
        
        @Test
        @DisplayName("Very small positive side length works correctly")
        void verySmallSideLength() {
            double smallSideLength = 0.001;
            Cube cube = new Cube(smallSideLength);
            
            assertTrue(cube.getVolume() > 0);
            assertTrue(cube.getSurfaceArea() > 0);
            assertEquals(smallSideLength, cube.getSideLength(), DELTA);
        }
        
        @Test
        @DisplayName("Large side length works correctly")
        void largeSideLength() {
            double largeSideLength = 1000.0;
            Cube cube = new Cube(largeSideLength);
            
            assertTrue(cube.getVolume() > 0);
            assertTrue(cube.getSurfaceArea() > 0);
            assertEquals(largeSideLength, cube.getSideLength(), DELTA);
        }
        
        @Test
        @DisplayName("Mathematical constants work correctly")
        void mathematicalConstants() {
            double piSideLength = Math.PI;
            Cube cube = new Cube(piSideLength);
            
            assertEquals(Math.pow(Math.PI, 3), cube.getVolume(), DELTA);
            assertEquals(6 * Math.pow(Math.PI, 2), cube.getSurfaceArea(), DELTA);
        }
    }
    
    @Nested
    @DisplayName("Input Validation Tests")
    class InputValidationTests {
        
        @Test
        @DisplayName("Constructor with zero side length throws exception")
        void constructorWithZeroSideLength() {
            // Decision: Zero side length should throw IllegalArgumentException
            // because a cube with zero side length is geometrically meaningless
            assertThrows(IllegalArgumentException.class, () -> {
                new Cube(0.0);
            });
        }
        
        @Test
        @DisplayName("Constructor with negative side length throws exception")
        void constructorWithNegativeSideLength() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Cube(-5.0);
            });
        }
        
        @Test
        @DisplayName("Setter with zero side length throws exception")
        void setterWithZeroSideLength() {
            Cube cube = new Cube(1.0);
            
            assertThrows(IllegalArgumentException.class, () -> {
                cube.setSideLength(0.0);
            });
        }
        
        @Test
        @DisplayName("Setter with negative side length throws exception")
        void setterWithNegativeSideLength() {
            Cube cube = new Cube(1.0);
            
            assertThrows(IllegalArgumentException.class, () -> {
                cube.setSideLength(-2.5);
            });
        }
        
        @Test
        @DisplayName("Full constructor with invalid side length throws exception")
        void fullConstructorWithInvalidSideLength() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Cube("Test", "Red", -1.0);
            });
        }
        
        @Test
        @DisplayName("Exception messages are descriptive")
        void exceptionMessagesAreDescriptive() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new Cube(0.0);
            });
            
            assertTrue(exception.getMessage().contains("positive"));
            assertTrue(exception.getMessage().contains("greater than zero"));
        }
    }
    
    @Nested
    @DisplayName("Inheritance Testing")
    class InheritanceTests {
        
        @Test
        @DisplayName("Cube works as Shape3D reference")
        void cubeAsShape3D() {
            // Test polymorphic behavior
            Shape3D shape = new Cube("Poly Cube", "Green", 4.0);
            
            assertEquals("Poly Cube", shape.getName());
            assertEquals("Green", shape.getColor());
            assertTrue(shape.getVolume() > 0);
            assertTrue(shape.getSurfaceArea() > 0);
            
            // Test that calculations work through base class interface
            assertEquals(64.0, shape.getVolume(), DELTA); // 4³
            assertEquals(96.0, shape.getSurfaceArea(), DELTA); // 6 * 4²
        }
        
        @Test
        @DisplayName("Cube implements ThreeDimensionalShape interface")
        void cubeImplementsInterface() {
            ThreeDimensionalShape shape = new Cube(3.0);
            
            assertTrue(shape.getVolume() > 0);
            assertTrue(shape.getSurfaceArea() > 0);
            
            // Verify interface methods work correctly
            assertEquals(27.0, shape.getVolume(), DELTA); // 3³
            assertEquals(54.0, shape.getSurfaceArea(), DELTA); // 6 * 3²
        }
        
        @Test
        @DisplayName("Cube toString works through inheritance")
        void toStringThroughInheritance() {
            Shape3D shape = new Cube("Inherited", "Yellow", 1.5);
            String result = shape.toString();
            
            assertTrue(result.contains("Inherited"));
            assertTrue(result.contains("Yellow"));
            assertTrue(result.contains("1.50"));
        }
        
        @Test
        @DisplayName("Inherited name and color properties work correctly")
        void inheritedPropertiesWork() {
            Cube cube = new Cube("Test Name", "Test Color", 2.0);
            
            // Test inherited setters
            cube.setName("New Name");
            cube.setColor("New Color");
            
            assertEquals("New Name", cube.getName());
            assertEquals("New Color", cube.getColor());
        }
    }
    
    @Nested
    @DisplayName("Cube-Specific Features Tests")
    class CubeSpecificTests {
        
        @Test
        @DisplayName("Face diagonal formula verification")
        void faceDiagonalFormulaVerification() {
            // For a cube with side length s, face diagonal = s√2
            double[] testSides = {1.0, 2.0, 5.0, 10.0};
            
            for (double side : testSides) {
                Cube cube = new Cube(side);
                double expectedFaceDiagonal = side * Math.sqrt(2);
                assertEquals(expectedFaceDiagonal, cube.getFaceDiagonal(), DELTA,
                    "Face diagonal incorrect for side length " + side);
            }
        }
        
        @Test
        @DisplayName("Space diagonal formula verification")
        void spaceDiagonalFormulaVerification() {
            // For a cube with side length s, space diagonal = s√3
            double[] testSides = {1.0, 2.0, 5.0, 10.0};
            
            for (double side : testSides) {
                Cube cube = new Cube(side);
                double expectedSpaceDiagonal = side * Math.sqrt(3);
                assertEquals(expectedSpaceDiagonal, cube.getSpaceDiagonal(), DELTA,
                    "Space diagonal incorrect for side length " + side);
            }
        }
        
        @Test
        @DisplayName("Diagonal relationships are correct")
        void diagonalRelationships() {
            Cube cube = new Cube(6.0);
            double sideLength = cube.getSideLength();
            double faceDiagonal = cube.getFaceDiagonal();
            double spaceDiagonal = cube.getSpaceDiagonal();
            
            // Verify relationships between dimensions
            assertEquals(sideLength * Math.sqrt(2), faceDiagonal, DELTA);
            assertEquals(sideLength * Math.sqrt(3), spaceDiagonal, DELTA);
            assertTrue(spaceDiagonal > faceDiagonal);
            assertTrue(faceDiagonal > sideLength);
        }
    }
    
    @Nested
    @DisplayName("Edge Cases and Special Scenarios")
    class EdgeCasesTests {
        
        @Test
        @DisplayName("Multiple cubes have independent properties")
        void independentCubes() {
            Cube cube1 = new Cube("Cube1", "Red", 1.0);
            Cube cube2 = new Cube("Cube2", "Blue", 2.0);
            
            assertNotEquals(cube1.getSideLength(), cube2.getSideLength());
            assertNotEquals(cube1.getName(), cube2.getName());
            assertNotEquals(cube1.getColor(), cube2.getColor());
            assertNotEquals(cube1.getVolume(), cube2.getVolume());
        }
        
        @Test
        @DisplayName("Cube calculations remain consistent after property changes")
        void calculationsAfterPropertyChanges() {
            Cube cube = new Cube(2.0);
            double originalVolume = cube.getVolume();
            
            cube.setName("Changed Name");
            cube.setColor("Changed Color");
            
            // Volume should remain the same since side length didn't change
            assertEquals(originalVolume, cube.getVolume(), DELTA);
            
            // Now change side length and verify volume changes
            cube.setSideLength(3.0);
            assertNotEquals(originalVolume, cube.getVolume(), DELTA);
            assertEquals(27.0, cube.getVolume(), DELTA); // 3³
        }
        
        @Test
        @DisplayName("Scaling properties work correctly")
        void scalingProperties() {
            Cube smallCube = new Cube(1.0);
            Cube largeCube = new Cube(2.0);
            
            // When side length doubles, volume should increase by factor of 8
            assertEquals(8.0, largeCube.getVolume() / smallCube.getVolume(), DELTA);
            
            // When side length doubles, surface area should increase by factor of 4
            assertEquals(4.0, largeCube.getSurfaceArea() / smallCube.getSurfaceArea(), DELTA);
        }
    }
}