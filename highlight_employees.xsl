<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html"/>

    <xsl:template match="/employees">
        <html>
            <head>
                <title>Employee Salaries</title>
            </head>
            <body>
                <table border="1">
                    <tr>
                        <th>Name</th>
                        <th>Salary</th>
                    </tr>
                    <xsl:for-each select="employee">
                        <xsl:variable name="salary" select="salary"/>
                        <xsl:choose>
                            <xsl:when test="$salary &gt; 50000">
                                <tr style="background-color: pink;">
                                    <td><xsl:value-of select="name"/></td>
                                    <td><xsl:value-of select="salary"/></td>
                                </tr>
                            </xsl:when>
                            <xsl:otherwise>
                                <tr>
                                    <td><xsl:value-of select="name"/></td>
                                    <td><xsl:value-of select="salary"/></td>
                                </tr>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
