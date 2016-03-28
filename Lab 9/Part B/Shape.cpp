#include <iostream>
#include "Shape.h"
#include "Point.h"
#include <string.h>

using namespace std;

Shape::Shape(double x, double y, char* name) : origin(x,y) {
	shapeName = new char[strlen(name) + 1];
	strcpy(shapeName, name);
}

Shape::~Shape() {
	delete[] shapeName;
}

Shape::Shape(Shape& s) : origin(s.getOrigin().getXCoord(), s.getOrigin().getYCoord()) {
	origin = s.getOrigin();
	shapeName = s.getName();
}

Point Shape::getOrigin() {
	return origin;
}

void Shape::setOrigin(Point p) {
	origin = p;
}

char* Shape::getName() {
	return shapeName;
}

void Shape::setName(char* name) {
	shapeName = new char[strlen(name) + 1];
	strcpy(shapeName, name);
}

double Shape::distance(Shape& s1) {
	return origin.distance(s1.getOrigin());
}

double Shape::distance(Shape& s1, Shape& s2) {
	return Point::distance(s1.getOrigin(), s2.getOrigin());
}

void Shape::move(double dx, double dy) {
	origin.setXCoord(origin.getXCoord() + dx);
	origin.setYCoord(origin.getYCoord() + dy);
}

void Shape::display() {
	cout << "\nShape Name: ";

	for (int i = 0; i < strlen(shapeName); i++) {
		cout << shapeName[i];
	}

	cout << endl;

	origin.display();
}

//Part 2 Additions
double Shape::area() {}
double Shape::perimeter() {}