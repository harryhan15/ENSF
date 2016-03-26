#ifndef RECTANGLE_H
#define RECTANGLE_H

class Rectangle : virtual public Shape {
private:
	double side_a;
	double side_b;
public:
	Rectangle(double x, double y, double sideA, double sideB, char* shapeName);
	Rectangle(Rectangle& r);
	Rectangle & operator=(Rectangle& r);

	double area();
	double perimeter();

	double getSideA();
	void setSideA(double sideA);

	double getSideB();
	void setSideB(double sideB);

	void display();
};

#endif