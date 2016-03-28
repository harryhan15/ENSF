#ifndef POINT_H
#define POINT_H

class Point {
private:
	double xCoord;
	double yCoord;
	int id;
	static int point;
public:
	Point(double x, double y);
	~Point();

	void setXCoord(double x);
	void setYCoord(double y);

	double getXCoord();
	double getYCoord();
	double getID();

	static int counter();

	double distance(Point p1);
	static double distance(Point p1, Point p2);

	void display();
};

#endif