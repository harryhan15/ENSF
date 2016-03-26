#include <iostream>
#include "Point.h"
#include <math.h>

using namespace std;

int Point::point = 0;

Point::Point(double x, double y) {
	xCoord = x;
	yCoord = y;

	id = 1000 + point;
	point++;
}

void Point::setXCoord(double x) {
	xCoord = x;
}

void Point::setYCoord(double y) {
	yCoord = y;
}

double Point::getXCoord() {
	return xCoord;
}

double Point::getYCoord() {
	return yCoord;
}

double Point::getID() {
	return id;
}

int Point::counter() {
	return point;
}

double Point::distance(Point p1) {
	return sqrt(pow(xCoord - p1.getXCoord(),2) + pow(yCoord - p1.getYCoord(),2));
}

double Point::distance(Point p1, Point p2) {
	return sqrt(pow(p1.getXCoord() - p2.getXCoord(), 2) + pow(p1.getYCoord() - p2.getYCoord(), 2));
}

void Point::display() {
	cout << "X-Coordinate: " << xCoord << endl;
	cout << "Y-Coordinate: " << yCoord << endl;
}