#include <iostream>
#include "Shape.h"
#include "Point.h"
#include "Square.h"
#include <string.h>

using namespace std;

Square::Square(char* shapeName, double x, double y, double side) : Shape(x, y, shapeName) {
	side_a = side;
}

Square::~Square() {}

Square::Square(Square& s) : Shape(s.getOrigin().getXCoord(), s.getOrigin().getYCoord(), s.getName()) {
	side_a = s.getSideA();
}

double Square::area() {
	return side_a * side_a;
}

double Square::perimeter() {
	return side_a * 4;
}

double Square::getSideA() {
	return side_a;
}

void Square::setSideA(double side) {
	side_a = side;
}

void Square::display() {
	Shape::display();
}