#ifndef CIRCLE_H
#define CIRCLE_H

class Circle : virtual public Shape {
private:
	double radius;
public:
	Circle(double x, double y, double rad, char* shapeName);
	~Circle();
	Circle(Circle& c);

	double area();
	double perimeter();

	double getRadius();
	void setRadius(double rad);

	void display();
};

#endif